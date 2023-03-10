package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.dto.PhotoSessionDto;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import com.gmail.serhiisemiv.repository.PhotoSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoSessionService {
    private final PhotoSessionRepository photoSessionRepository;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public PhotoSessionService(PhotoSessionRepository photoSessionRepository) {
        this.photoSessionRepository = photoSessionRepository;
    }

    public void savePhotoSession(PhotoSession photoSession) {
        if (photoSession == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            debug.debug("Start saving photo session{}", photoSession);
            photoSessionRepository.save(photoSession);
            debug.debug("Photo session is saved{}", photoSession);
        } catch (NumberFormatException e) {
            error.error("Cant save photo session  - " + photoSession, e);
            throw new ServiceException("Cant save photo session");
        }
    }

    public PhotoSession findPhotoSessionById(int id) {
        debug.debug("Start returned photo session with id - {}", id);
        Optional<PhotoSession> photoSession = photoSessionRepository.findById(id);
        if (photoSession.isEmpty()) {
            error.error("Photo session is not present", new ServiceException("Can't find photo session with id - " + id));
            throw new ServiceException("Can't find photo session with id - " + id);
        }
        debug.debug("Photo session was returned - {}", photoSession.get());
        return photoSession.get();
    }

    public List<PhotoSession> findAllPhotoSessions() {
        debug.debug("Starting returning all photo sessions");
        try {
            List<PhotoSession> photoSessions = photoSessionRepository.findAll();
            debug.debug("All photo sessions was returned");
            return photoSessions;
        } catch (NullPointerException e) {
            error.error("Can't find any photo sessions - " + e.getMessage(), e);
            throw new ServiceException("Can't find any photos sessions");
        }
    }

    public void editPhotoSession(int id, PhotoSessionDto photoSessionDto){
        PhotoSession photoSession = this.findPhotoSessionById(id);
        debug.debug("Starting edit Photo session with id - {}",id );
        photoSession.setName(photoSessionDto.getName());
        this.savePhotoSession(photoSession);
        debug.debug("Photo Session edited - {} ",id );
    }

    public void deletePhotoSessionById(int id) {
        debug.debug("Starting delete photo session with id - {}", id);
        try {
            photoSessionRepository.deleteById(id);
            debug.debug("Photo sessions was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove photo session with id - " + id, e);
            throw new ServiceException("Can't delete photo session with id");
        }
    }

    public PhotoSession createNewPhotoSession(PhotoSessionDto photoSessionDto){
        PhotoSession photoSession = new PhotoSession();
        photoSession.setName(photoSessionDto.getName());
        return photoSession;
    }
}
