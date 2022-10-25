package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import com.gmail.serhiisemiv.repository.CostumerFeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CostumerFeedbackService {
    private final CostumerFeedbackRepository costumerFeedbackRepository;
    private final CostumerService costumerService;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public CostumerFeedbackService(CostumerFeedbackRepository costumerFeedbackRepository, CostumerService costumerService) {
        this.costumerFeedbackRepository = costumerFeedbackRepository;
        this.costumerService = costumerService;
    }

    public void saveCostumerFeedback(CostumerFeedback costumerFeedback) throws ServiceException {
        if (costumerFeedback == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            debug.debug("Start saving new costumer feedback - {}", costumerFeedback);
            costumerFeedbackRepository.save(costumerFeedback);
            debug.debug("Costumer feedback is saved{}", costumerFeedback);
        } catch (NumberFormatException e) {
            error.error("Cant save costumer  - " + costumerFeedback, e);
            throw new ServiceException("Cant save costumer feedback");
        }
    }

    public CostumerFeedback findCostumerFeedbackById(int id) {
        debug.debug("Start returned costumer feedback with id - {}", id);
        Optional<CostumerFeedback> costumerFeedback = costumerFeedbackRepository.findById(id);
        error.error("Costumer feedback is not present", new ServiceException("Can't find costumer feedback with id - " + id));
        if (costumerFeedback.isEmpty()) {
            throw new ServiceException("Can't find costumer feedback with id");
        }
        debug.debug("Costumer feedback was returned - {}", costumerFeedback.get());
        return costumerFeedback.get();
    }

    public List<CostumerFeedback> findAllCostumerFeedbacks() {
        debug.debug("Starting returning all costumer feedbacks");
        try {
            List<CostumerFeedback> costumerFeedbacks = costumerFeedbackRepository.findAll();
            debug.debug("All costumer feedback was returned");
            return costumerFeedbacks;
        } catch (NullPointerException e) {
            error.error("Can't find any costumer feedbacks - " + e.getMessage(), e);
            throw new ServiceException("Can't find any costumer feedback");
        }
    }

    public void deleteCostumerFeedbackById(int id) throws ServiceException {
        debug.debug("Starting delete costumer feedback with id - {}", id);
        try {
            costumerFeedbackRepository.deleteById(id);
            debug.debug("Costumer feedback was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove costumer with id - " + id, e);
            throw new ServiceException("Can't delete costumer feedback with id - "+id);
        }
    }

    public CostumerFeedback createNewCostumerFeedback(CostumerFeedbackDto feedbackDto){
        CostumerFeedback feedback = new CostumerFeedback();
        feedback.setFirstName(feedbackDto.getFirstName());
        feedback.setLastName(feedbackDto.getLastName());
        feedback.setEmail(feedbackDto.getEmail());
        feedback.setCreationDate(new Date().getTime());
        feedback.setFeedback(feedbackDto.getFeedback());
        feedback.setGrade(feedbackDto.getGrade());
        return feedback;
    }
}
