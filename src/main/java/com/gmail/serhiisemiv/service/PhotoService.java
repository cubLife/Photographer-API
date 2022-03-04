package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Photo;
import com.gmail.serhiisemiv.repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void savePhoto(Photo photo) {
        if (photo == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new photo {}", photo.getName());
            photoRepository.save(photo);
            debug.debug("Photo is saved{}", photo.getImage());
        } catch (NumberFormatException e) {
            error.error("Cant save photo  - " + photo.getName(), e);
            throw new ServiceException("Cant save  photo");
        }
    }

    public void saveAllPhotos(List<Photo> photos) {
        if (photos == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new photos");
            photoRepository.saveAll(photos);
            debug.debug("Photos is saved");
        } catch (NumberFormatException e) {
            error.error("Cant save photos", e);
            throw new ServiceException("Cant save  photos");
        }
    }

    public Photo findPhotoById(int id) {
        info.info("Start returned photo with id - {}", id);
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isEmpty()) {
            error.error("Photo is not present", new ServiceException("Can't find photo with id - " + id));
            throw new ServiceException("Can't find photo with id");
        }
        debug.debug("Photo was returned - {}", photo.get().getId());
        return photo.get();
    }

    public List<Photo> findAllPhotos() {
        info.info("Starting returning all photos");
        try {
            List<Photo> photos = photoRepository.findAll();
            debug.debug("All photos was returned");
            return photos;
        } catch (NullPointerException e) {
            error.error("Can't find any photos - " + e.getMessage(), e);
            throw new ServiceException("Can't find any photos");
        }
    }

    public List<Photo> findAllByAlbumId(int albumId){
        info.info("Starting returning all photos with album id {}",albumId);
        try {
            List<Photo> photos = photoRepository.findAllByPhotoAlbum_Id(albumId);
            debug.debug("All photos with album id is returned id {}", albumId);
            return photos;
        }catch (NullPointerException e){
            error.error("Can't find any photos with photo album id - "+albumId + e.getMessage(), e);
            throw new ServiceException("Can't find any photos");
        }
    }

    public void deletePhotoById(int id) {
        info.info("Starting delete photo with id - {}", id);
        try {
            photoRepository.deleteById(id);
            debug.debug("Photo was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove photo with id - " + id, e);
            throw new ServiceException("Can't delete photo with id");
        }
    }

    public Photo createNewPhoto(MultipartFile file) {
        Photo photo = new Photo();
        try {
            photo.setName(file.getOriginalFilename());
            photo.setSize(file.getSize());
            photo.setImage(file.getBytes());
        } catch (IOException e) {
            error.error("Can't create photo. " + e.getMessage(), e);
        }
        return photo;
    }
}
