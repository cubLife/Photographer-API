package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Photographer;
import com.gmail.serhiisemiv.repository.PhotographerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotographerService {
    private final PhotographerRepository photographerRepository;
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PhotographerService(PhotographerRepository photographerRepository) {
        this.photographerRepository = photographerRepository;
    }

    public void savePhotographer(Photographer photographer) {
        if (photographer == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new photographer{}", photographer);
            photographerRepository.save(photographer);
            debug.debug("Photographer is saved{}", photographer);
        } catch (NumberFormatException e) {
            error.error("Cant save photographer - " + photographer, e);
            throw new ServiceException("Cant save photographer");
        }
    }

    public Photographer findPhotographerById(int id) {
        info.info("Start returned photographer with id - {}", id);
        Optional<Photographer> photographer = photographerRepository.findById(id);
        if (photographer.isEmpty()) {
            error.error("Photographer is not present", new ServiceException("Can't find photographer with id - " + id));
            throw new ServiceException("Can't find photographer with id");
        }
        debug.debug("Photographer was returned - {}", photographer.get());
        return photographer.get();
    }

    public List<Photographer> findAllPhotographers() {
        info.info("Starting returning all photographers");
        try {
            List<Photographer> photographers = photographerRepository.findAll();
            debug.debug("All photographers was returned");
            return photographers;
        } catch (NullPointerException e) {
            error.error("Can't find any photographers - " + e.getMessage(), e);
            throw new ServiceException("Cant find any photographers");
        }
    }

    public void deletePhotographerById(int id) {
        info.info("Starting delete photographer with id - {}", id);
        try {
            photographerRepository.deleteById(id);
            debug.debug("Photographer was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove photographer with id - " + id, e);
            throw new ServiceException("Can't delete photographer with id");
        }
    }
}
