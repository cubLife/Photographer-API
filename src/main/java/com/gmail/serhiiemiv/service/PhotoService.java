package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.Photo;
import com.gmail.serhiiemiv.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void savePhoto(Photo photo) {
        if (photo == null) {
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            photoRepository.save(photo);
        } catch (NumberFormatException e) {
            throw new ServiceException("Cant save costumer photo " + photo);
        }
    }

    public Photo findPhotoById(int id) {
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isEmpty()) {
            throw new ServiceException("Can't find photo with id - " + id);
        }
        return photo.get();
    }

    public List<Photo> findAllPhotos() {
        try {
            return photoRepository.findAll();
        } catch (NullPointerException e) {
            throw new ServiceException("Can't find any photos");
        }
    }

    public void deletePhotoById(int id) {
        try {
            photoRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ServiceException("Can't delete photo with id = " + id);
        }
    }
}
