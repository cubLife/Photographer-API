package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import com.gmail.serhiisemiv.repository.PhotoSessionPackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoSessionPackageService {
    private final PhotoSessionPackageRepository packageRepository;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");


    public PhotoSessionPackageService(PhotoSessionPackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public void savePhotoSessionPackage(PhotoSessionPackage photoSessionPackage) {
        if (photoSessionPackage == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            debug.debug("Start saving new photo session package{}", photoSessionPackage);
            packageRepository.save(photoSessionPackage);
            debug.debug("Photo session is saved{}", photoSessionPackage);
        } catch (NumberFormatException e) {
            error.error("Cant save photo session package - " + photoSessionPackage, e);
            throw new ServiceException("Cant save photo session package");
        }
    }

    public PhotoSessionPackage findPhotoSessionPackageById(int id) {
        debug.debug("Start returned photo session package with id - {}", id);
        Optional<PhotoSessionPackage> photoSessionPackage = packageRepository.findById(id);
        if (photoSessionPackage.isEmpty()) {
            error.error("Photo session package is not present", new ServiceException("Can't find photo session package with id - " + id));
            throw new ServiceException("Can't find photo session package with id - " + id);
        }
        debug.debug("Photo session package was returned - {}", photoSessionPackage.get());
        return photoSessionPackage.get();
    }

    public List<PhotoSessionPackage> findAllPhotoSessionPackages() {
        debug.debug("Starting returning all photo session packages");
        try {
            List<PhotoSessionPackage> photoSessionPackages = packageRepository.findAll();
            debug.debug("All photo sessions packages was returned");
            return photoSessionPackages;
        } catch (NullPointerException e) {
            error.error("Can't find any photo session packages - " + e.getMessage(), e);
            throw new ServiceException("Can't find any photos sessions packages");
        }
    }

    public void editPhotoSessionPackageById(int id, PhotoSessionPackageDto sessionPackageDto) {
        PhotoSessionPackage sessionPackage = this.findPhotoSessionPackageById(id);
        debug.debug("Starting edit Photo session Package with id - {}", id);
        edit(sessionPackage, sessionPackageDto);
        debug.debug("Photo Session package edited - {} ", id);
        this.savePhotoSessionPackage(sessionPackage);

    }

    public void deletePhotoSessionPackageById(int id) {
        debug.debug("Starting delete photo session package with id - {}", id);
        try {
            packageRepository.deleteById(id);
            debug.debug("Photo session package was deleted with id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove photo session package with id - " + id, e);
            throw new ServiceException("Can't delete photo session package with id");
        }
    }

    private void edit(PhotoSessionPackage sessionPackage, PhotoSessionPackageDto sessionPackageDto) {
        if ( sessionPackageDto.getName() != null && !sessionPackageDto.getName().isEmpty()) {
            sessionPackage.setName(sessionPackageDto.getName());
        }
        if (sessionPackageDto.getNumberPhotos() > 0) {
            sessionPackage.setNumberPhotos(sessionPackageDto.getNumberPhotos());
        }
        if (sessionPackageDto.getPrice() > 0) {
            sessionPackage.setPrice(sessionPackageDto.getPrice());
        }
        if (sessionPackageDto.getDuration() > 0) {
            sessionPackage.setDuration(sessionPackageDto.getDuration());
        }
    }
}
