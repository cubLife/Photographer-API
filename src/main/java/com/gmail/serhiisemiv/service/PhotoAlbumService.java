package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import com.gmail.serhiisemiv.repository.PhotoAlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoAlbumService {
    private final PhotoAlbumRepository photoAlbumRepository;
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PhotoAlbumService(PhotoAlbumRepository photoAlbumRepository) {
        this.photoAlbumRepository = photoAlbumRepository;
    }

    public void savePhotoAlbum(PhotoAlbum photoAlbum) {
        if (photoAlbum == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new photo album {}", photoAlbum);
            photoAlbumRepository.save(photoAlbum);
            debug.debug("Photo album is saved{}", photoAlbum);
        } catch (NumberFormatException e) {
            error.error("Cant save photo album  - " + photoAlbum, e);
            throw new ServiceException("Cant save photo album");
        }
    }

    public PhotoAlbum findPhotoAlbumById(int id) {
        info.info("Start returned photo album with id - {}", id);
        Optional<PhotoAlbum> photoAlbum = photoAlbumRepository.findById(id);
        if (photoAlbum.isEmpty()) {
            error.error("Photo album is not present", new ServiceException("Can't find photo album with id - " + id));
            throw new ServiceException("Can't find photo album with id");
        }
        debug.debug("Photo album was returned - {}", photoAlbum.get());
        return photoAlbum.get();
    }

    public List<PhotoAlbum> findAllPhotoAlbums() {
        info.info("Starting returning all photo albums");
        try {
            List<PhotoAlbum> photoAlbums = photoAlbumRepository.findAll();
            debug.debug("All photo albums was returned");
            return photoAlbums;
        } catch (NullPointerException e) {
            error.error("Can't find any photo albums - " + e.getMessage(), e);
            throw new ServiceException("Cant find any photo albums");
        }
    }

    public void deletePhotoAlbumById(int id) {
        info.info("Starting delete photo album with id - {}", id);
        try {
            photoAlbumRepository.deleteById(id);
            debug.debug("Photo album was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove photo album with id - " + id, e);
            throw new ServiceException("Can't delete photo album with id");
        }
    }
}