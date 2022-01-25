package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.CostumerFeedback;
import com.gmail.serhiiemiv.repository.CostumerFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CostumerFeedbackService {
    private final CostumerFeedbackRepository costumerFeedbackRepository;

    @Autowired
    public CostumerFeedbackService(CostumerFeedbackRepository costumerFeedbackRepository) {
        this.costumerFeedbackRepository = costumerFeedbackRepository;
    }

    public void saveCostumerFeedback(CostumerFeedback costumerFeedback) throws ServiceException {
        if (costumerFeedback == null) {
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            costumerFeedbackRepository.save(costumerFeedback);
        } catch (NumberFormatException e) {
            throw new ServiceException("Cant save costumer feedback " + costumerFeedback);
        }
    }

    public CostumerFeedback findCostumerFeedbackById(int id) {
        Optional<CostumerFeedback> costumerFeedback=  costumerFeedbackRepository.findById(id);
        if(costumerFeedback.isEmpty()){
            throw new ServiceException("Can't find costumer feedback with id  - "+id);
        }
            return costumerFeedback.get();
    }

    public List<CostumerFeedback> findAllCostumerFeedbacks() {
        try {
            return costumerFeedbackRepository.findAll();
        } catch (NullPointerException e) {
            throw new ServiceException("Can't find any costumer feedback");
        }
    }

    public void deleteCostumerFeedbackById(int id) throws ServiceException {
        try {
            costumerFeedbackRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ServiceException("Can't delete costumer feedback with id = " + id);
        }
    }
}
