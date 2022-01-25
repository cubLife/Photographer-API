package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.PhotoSession;
import com.gmail.serhiiemiv.repository.PhotoSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoSessionService {
    private final PhotoSessionRepository photoSessionRepository;

    @Autowired
    public PhotoSessionService(PhotoSessionRepository photoSessionRepository) {
        this.photoSessionRepository = photoSessionRepository;
    }

    public void savePhotoSession(PhotoSession photoSession) {
        if (photoSession == null) {
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            photoSessionRepository.save(photoSession);
        } catch (NumberFormatException e) {
            throw new ServiceException("Cant save costumer photo session " + photoSession);
        }
    }

    public PhotoSession findPhotoSessionById(int id) {
        Optional<PhotoSession> photoSession = photoSessionRepository.findById(id);
        if (photoSession.isEmpty()) {
            throw new ServiceException("Can't find photo session with id - " + id);
        }
        return photoSession.get();
    }

    public List<PhotoSession> findAllPhotoSessions() {
        try {
            return photoSessionRepository.findAll();
        } catch (NullPointerException e) {
            throw new ServiceException("Can't find any photos sessions");
        }
    }

    public void deletePhotoSessionById(int id) {
        try {
            photoSessionRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ServiceException("Can't delete photo session with id = " + id);
        }
    }
}
