package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.Costumer;
import com.gmail.serhiiemiv.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CostumerService {
    private final CostumerRepository costumerRepository;

    @Autowired
    public CostumerService(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    public void saveCostumer(Costumer costumer) {
        if (costumer == null) {
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            costumerRepository.save(costumer);
        } catch (NumberFormatException e) {
            throw new ServiceException("Cant save costumer " + costumer);
        }
    }

    public Costumer findCostumerById(int id) {
        Optional<Costumer> costumer = costumerRepository.findById(id);
        if (costumer.isEmpty()) {
            throw new ServiceException("Can't find costumer with id - " + id);
        }
        return costumer.get();
    }

    public List<Costumer> findAllCostumers() {
        try {
            return costumerRepository.findAll();
        } catch (NullPointerException e) {
            throw new ServiceException("Can't find any costumer. \n", e);
        }
    }

    public void deleteCostumerById(int id) {
        try {
            costumerRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ServiceException("Can't remove costumer with id - " + id);
        }
    }
}
