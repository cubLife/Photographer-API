package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.dto.PhotoAlbumDto;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Photo;
import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import com.gmail.serhiisemiv.modeles.PhotoSession;
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
    private final PhotoSessionService photoSessionService;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public PhotoAlbumService(PhotoAlbumRepository photoAlbumRepository, PhotoSessionService photoSessionService) {
        this.photoAlbumRepository = photoAlbumRepository;

        this.photoSessionService = photoSessionService;
    }

    public void savePhotoAlbum(PhotoAlbum photoAlbum) {
        if (photoAlbum == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            debug.debug("Start saving new photo album {}", photoAlbum);
            photoAlbumRepository.save(photoAlbum);
            debug.debug("Photo album is saved{}", photoAlbum);
        } catch (NumberFormatException e) {
            error.error("Cant save photo album  - " + photoAlbum, e);
            throw new ServiceException("Cant save photo album");
        }
    }

    public PhotoAlbum findPhotoAlbumById(int id) {
        debug.debug("Start returned photo album with id - {}", id);
        Optional<PhotoAlbum> photoAlbum = photoAlbumRepository.findById(id);
        if (photoAlbum.isEmpty()) {
            error.error("Photo album is not present", new ServiceException("Can't find photo album with id - " + id));
            throw new ServiceException("Can't find photo album with id");
        }
        debug.debug("Photo album was returned - {}", photoAlbum.get());
        return photoAlbum.get();
    }

    public List<PhotoAlbum> findAllPhotoAlbums() {
        debug.debug("Starting returning all photo albums");
        try {
            List<PhotoAlbum> photoAlbums = photoAlbumRepository.findAll();
            debug.debug("All photo albums was returned");
            return photoAlbums;
        } catch (NullPointerException e) {
            error.error("Can't find any photo albums - " + e.getMessage(), e);
            throw new ServiceException("Cant find any photo albums");
        }
    }

    public List<PhotoAlbum> findByPhotoSessionId(int id){
        debug.debug("Starting returning all photo albums by Photo Session id");
        try {
            List<PhotoAlbum> photoAlbums = photoAlbumRepository.findByPhotoSession_Id(id);
            debug.debug("All photo albums by Photo Session id was returned");
            return photoAlbums;
        } catch (NullPointerException e) {
            error.error("Can't find any photo albums by Photo Session id - " + e.getMessage(), e);
            throw new ServiceException("Cant find any photo albums by Photo Session id");
        }
    }


    public void deletePhotoAlbumById(int id) {
        debug.debug("Starting delete photo album with id - {}", id);
        try {
            photoAlbumRepository.deleteById(id);
            debug.debug("Photo album was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove photo album with id - " + id, e);
            throw new ServiceException("Can't delete photo album with id");
        }
    }

    public PhotoAlbum createPhotoAlbum(PhotoAlbumDto photoAlbumDto){
        PhotoSession photoSession = photoSessionService.findPhotoSessionById(photoAlbumDto.getPhotoSessionId());
        return new PhotoAlbum(photoAlbumDto.getName(),photoSession);
    }
}
