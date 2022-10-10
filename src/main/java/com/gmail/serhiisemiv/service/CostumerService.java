package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.repository.CostumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CostumerService {
    private final CostumerRepository costumerRepository;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public CostumerService(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    public void saveCostumer(Costumer costumer) {
        if (costumer == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            debug.debug("Start saving new costumer {}", costumer);
            costumerRepository.save(costumer);
            debug.debug("Costumer is saved{}", costumer);
        } catch (NumberFormatException e) {
            error.error("Cant save costumer  - " + costumer, e);
            throw new ServiceException("Cant save costumer");
        }
    }

    public Costumer findCostumerById(int id) {
        debug.debug("Start returned costumer with id - {}", id);
        Optional<Costumer> costumer = costumerRepository.findById(id);
        if (costumer.isEmpty()) {
            error.error("Costumer is not present", new ServiceException("Can't find costumer with id - " + id));
            throw new ServiceException("Can't find costumer with id - "+id);
        }
        debug.debug("Costumer was returned - {}", costumer.get());
        return costumer.get();
    }

    public Costumer findCostumerByEmail(String email) {
        debug.debug("Start returned costumer with email - {}", email);
        Optional<Costumer> costumer = costumerRepository.findCostumerByEmail(email);
        if (costumer.isEmpty()) {
            error.error("Costumer is not present", new ServiceException("Can't find costumer with email - " + email));
            throw new ServiceException("Can't find costumer with email - "+email);
        }
        debug.debug("Costumer was returned - {}", costumer.get());
        return costumer.get();
    }

    public List<Costumer> findAllCostumers() {
        debug.debug("Starting returning all costumers");
        try {
            List<Costumer> costumers = costumerRepository.findAll();
            debug.debug("All costumers was returned");
            return costumers;
        } catch (NullPointerException e) {
            error.error("Can't find any costumers - " + e.getMessage(), e);
            throw new ServiceException("Can't find any costumers");
        }
    }

    public boolean existsCostumerByEmail(String email){
        debug.debug("Checking if exists costumer by email");
        return costumerRepository.existsCostumerByEmail(email);
    }

    public void deleteCostumerById(int id) {
        debug.debug("Starting delete costumer with id - {}", id);
        try {
            costumerRepository.deleteById(id);
            debug.debug("Costumer was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove costumer with id - " + id, e);
            throw new ServiceException("Can't remove costumer with id");
        }
    }
}
