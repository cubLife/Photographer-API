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
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

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
            info.info("Start saving new photo session{}", photoSession);
            photoSessionRepository.save(photoSession);
            debug.debug("Photo session is saved{}", photoSession);
        } catch (NumberFormatException e) {
            error.error("Cant save photo session  - " + photoSession, e);
            throw new ServiceException("Cant save photo session");
        }
    }

    public PhotoSession findPhotoSessionById(int id) {
        info.info("Start returned photo session with id - {}", id);
        Optional<PhotoSession> photoSession = photoSessionRepository.findById(id);
        if (photoSession.isEmpty()) {
            error.error("Photo session is not present", new ServiceException("Can't find photo session with id - " + id));
            throw new ServiceException("Can't find photo session with id - " + id);
        }
        debug.debug("Photo session was returned - {}", photoSession.get());
        return photoSession.get();
    }

    public List<PhotoSession> findAllPhotoSessions() {
        info.info("Starting returning all photo sessions");
        try {
            List<PhotoSession> photoSessions = photoSessionRepository.findAll();
            debug.debug("All photo sessions was returned");
            return photoSessions;
        } catch (NullPointerException e) {
            error.error("Can't find any photo sessions - " + e.getMessage(), e);
            throw new ServiceException("Can't find any photos sessions");
        }
    }

    public void deletePhotoSessionById(int id) {
        info.info("Starting delete photo session with id - {}", id);
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
        photoSession.setPrice(photoSessionDto.getPrice());
        photoSession.setType(photoSessionDto.getType());
        photoSession.setDuration(photoSessionDto.getDuration());
        return photoSession;
    }
}
