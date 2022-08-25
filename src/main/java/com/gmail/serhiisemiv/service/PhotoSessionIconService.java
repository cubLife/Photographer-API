package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.AvatarImage;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import com.gmail.serhiisemiv.modeles.PhotoSessionIcon;
import com.gmail.serhiisemiv.repository.PhotoSessionIconRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoSessionIconService {
    private final PhotoSessionIconRepository repository;
    private final PhotoSessionService sessionService;
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    public PhotoSessionIconService(PhotoSessionIconRepository repository, PhotoSessionService sessionService) {
        this.repository = repository;
        this.sessionService = sessionService;
    }

    public void save(PhotoSessionIcon sessionIcon) {
        if (sessionIcon == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new Photo session Icon");
            repository.save(sessionIcon);
            debug.debug("Photo session icon is saved");
        } catch (NumberFormatException e) {
            error.error("Cant save Photo session icon  - ", e);
            throw new ServiceException("Can't save Photo session icon");
        }
    }

    public PhotoSessionIcon findByPhotoSessionId(int id){
        info.info("Start to returning Photo Session Icon with Photo Session id {}", id);
        Optional<PhotoSessionIcon> sessionIcon = repository.findByPhotoSession_Id(id);
        if (sessionIcon.isEmpty()) {
            error.error("Photo Session Icon is not present", new ServiceException("Can't find Photo Session Icon with Photo Session Id - " + id));
            throw new ServiceException("Can't find Photo Session Icon with Photo Session Id - " + id);
        }
        debug.debug("Photo Session Icon was returned - {}", sessionIcon.get().getId());
        return sessionIcon.get();
    }

    public PhotoSessionIcon findById(int id) {
        info.info("Start to returning Photo Session with id - {}", id);
        Optional<PhotoSessionIcon> sessionIcon = repository.findById(id);
        if (sessionIcon.isEmpty()) {
            error.error("Photo Session Icon is not present", new ServiceException("Can't find Photo Session Icon with id - " + id));
            throw new ServiceException("Can't find avatar image with id - " + id);
        }
        debug.debug("Photo Session icon was returned - {}", sessionIcon.get().getId());
        return sessionIcon.get();
    }

    public void deletePhotoSessionIconById(int id) throws ServiceException {
        info.info("Start to delete Photo Session Icon with id - {}", id);
        try {
            repository.deleteById(id);
            debug.debug("Photo Session Icon was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't delete Photo Session Icon with id - " + id, e);
            throw new ServiceException("Can't delete Photo Session Icon with id - " + id);
        }
    }

    public PhotoSessionIcon createNew( MultipartFile file, int sessionId) throws IOException {
        info.info("Starting create new Photo Session Icon");
        PhotoSession photoSession =sessionService.findPhotoSessionById(sessionId);
        PhotoSessionIcon sessionIcon = new PhotoSessionIcon();
        sessionIcon.setPhotoSession(photoSession);
        sessionIcon.setPicture(file.getBytes());
        return sessionIcon;
    }

}
