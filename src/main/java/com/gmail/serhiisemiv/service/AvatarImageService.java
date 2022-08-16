package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.AvatarImage;
import com.gmail.serhiisemiv.repository.AvatarImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AvatarImageService {
    private final AvatarImageRepository avatarImageRepository;
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AvatarImageService(AvatarImageRepository avatarImageRepository) {
        this.avatarImageRepository = avatarImageRepository;
    }

    public void saveAvatarImage(AvatarImage avatarImage) {
        if (avatarImage == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new avatar Image image");
            avatarImageRepository.save(avatarImage);
            debug.debug("Avatar is saved");
        } catch (NumberFormatException e) {
            error.error("Cant save avatar image  - ", e);
            throw new ServiceException("Can't save avatar image");
        }
    }

    public AvatarImage findByPhotographerId(int id){
        info.info("Start to returning avatar image by photographer id {}", id);
        Optional<AvatarImage> avatarImage = avatarImageRepository.findByPhotographer_Id(id);
        if (avatarImage.isEmpty()) {
            error.error("Avatar image is not present", new ServiceException("Can't find avatar image photographer id - " + id));
            throw new ServiceException("Can't find avatar image with photographer id  - " + id);
        }
        debug.debug("Avatar image was returned - {}", avatarImage.get());
        return avatarImage.get();
    }

    public AvatarImage findAvatarImageById(int id) {
        info.info("Start to returning avatar image with id - {}", id);
        Optional<AvatarImage> avatarImage = avatarImageRepository.findById(id);
        if (avatarImage.isEmpty()) {
            error.error("Avatar image is not present", new ServiceException("Can't find avatar image with id - " + id));
            throw new ServiceException("Can't find avatar image with id - " + id);
        }
        debug.debug("Avatar image was returned - {}", avatarImage.get());
        return avatarImage.get();
    }

    public void deleteAvatarImageById(int id) throws ServiceException {
        info.info("Starting delete avatar image with id - {}", id);
        try {
            avatarImageRepository.deleteById(id);
            debug.debug("Avatar image was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't delete carousel image with id - " + id, e);
            throw new ServiceException("Can't delete carousel image with id - " + id);
        }
    }
}

