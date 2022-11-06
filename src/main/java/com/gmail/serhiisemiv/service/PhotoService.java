package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Photo;
import com.gmail.serhiisemiv.repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final PhotoAlbumService albumService;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public PhotoService(PhotoRepository photoRepository, PhotoAlbumService albumService) {
        this.photoRepository = photoRepository;
        this.albumService = albumService;
    }

    public void savePhoto(Photo photo) {
        if (photo == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            debug.debug("Start saving new photo {}", photo.getName());
            photoRepository.save(photo);
            debug.debug("Photo is saved{}", photo.getId());
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
            debug.debug("Start saving new photos");
            photoRepository.saveAll(photos);
            debug.debug("Photos is saved");
        } catch (NumberFormatException e) {
            error.error("Cant save photos", e);
            throw new ServiceException("Cant save  photos");
        }
    }

    public Photo findPhotoById(int id) {
        debug.debug("Start returned photo with id - {}", id);
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isEmpty()) {
            error.error("Photo is not present", new ServiceException("Can't find photo with id - " + id));
            throw new ServiceException("Can't find photo with id");
        }
        debug.debug("Photo was returned - {}", photo.get().getId());
        return photo.get();
    }

    public List<Photo> findAllPhotos() {
        debug.debug("Starting returning all photos");
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
        debug.debug("Starting returning all photos with album id {}",albumId);
        try {
            List<Photo> photos = photoRepository.findAllByPhotoAlbum_Id(albumId);
            debug.debug("All photos with album id is returned id {}", albumId);
            return photos;
        }catch (NullPointerException e){
            error.error("Can't find any photos with photo album id - "+albumId + e.getMessage(), e);
            throw new ServiceException("Can't find any photos");
        }
    }

    public Resource findFirstByAlbumId(int albumId){
        debug.debug("Starting returning first image with album id {}",albumId);
        try {
            Photo photo = photoRepository.findFirstByPhotoAlbum_Id(albumId);
            debug.debug("first image with album id is returned id {}", albumId);
            return new ByteArrayResource(photo.getPicture());
        }catch (NullPointerException e){
            error.error("Can't find any images with photo album id - "+albumId + e.getMessage(), e);
            throw new ServiceException("Can't find any images");
        }
    }


    public void deletePhotoById(int id) {
        debug.debug("Starting delete photo with id - {}", id);
        try {

            photoRepository.deleteById(id);
            debug.debug("Photo was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove photo with id - " + id, e);
            throw new ServiceException("Can't delete photo with id");
        }
    }

    public Photo createNewPhoto(MultipartFile file, int photoAlbumId) {
        Photo photo = new Photo();
        try {
            photo.setPhotoAlbum(albumService.findPhotoAlbumById(photoAlbumId));
            photo.setName(file.getOriginalFilename());
            photo.setSize(file.getSize());
            photo.setPicture(file.getBytes());
        } catch (IOException e) {
            error.error("Can't create photo. " + e.getMessage(), e);
        }
        return photo;
    }
}
