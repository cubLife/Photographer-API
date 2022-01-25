package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.PhotoAlbum;
import com.gmail.serhiiemiv.repository.PhotoAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoAlbumService {
    private final PhotoAlbumRepository photoAlbumRepository;

    @Autowired
    public PhotoAlbumService(PhotoAlbumRepository photoAlbumRepository) {
        this.photoAlbumRepository = photoAlbumRepository;
    }

    public void savePhotoAlbum(PhotoAlbum photoAlbum) {
        if (photoAlbum == null) {
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            photoAlbumRepository.save(photoAlbum);
        } catch (NumberFormatException e) {
            throw new ServiceException("Cant save costumer photo album " + photoAlbum);
        }
    }

    public PhotoAlbum findPhotoAlbumById(int id) {
        Optional<PhotoAlbum> photoAlbum = photoAlbumRepository.findById(id);
        if (photoAlbum.isEmpty()) {
            throw new ServiceException("Can't find photo album with id - " + id);
        }
        return photoAlbum.get();
    }

    public List<PhotoAlbum> findAllPhotoAlbums() {
        try {
            return photoAlbumRepository.findAll();
        } catch (NullPointerException e) {
            throw new ServiceException("Cant find any photo albums");
        }
    }

    public void deletePhotoAlbumById(int id) {
        try {
            photoAlbumRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ServiceException("Can't delete photo album with id - " + id);
        }
    }
}
