package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Costumer;
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
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

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
            info.info("Start saving new costumer feedback - {}", costumerFeedback);
            costumerFeedbackRepository.save(costumerFeedback);
            debug.debug("Costumer feedback is saved{}", costumerFeedback);
        } catch (NumberFormatException e) {
            error.error("Cant save costumer  - " + costumerFeedback, e);
            throw new ServiceException("Cant save costumer feedback");
        }
    }

    public CostumerFeedback findCostumerFeedbackById(int id) {
        info.info("Start returned costumer feedback with id - {}", id);
        Optional<CostumerFeedback> costumerFeedback = costumerFeedbackRepository.findById(id);
        error.error("Costumer feedback is not present", new ServiceException("Can't find costumer feedback with id - " + id));
        if (costumerFeedback.isEmpty()) {
            throw new ServiceException("Can't find costumer feedback with id");
        }
        debug.debug("Costumer feedback was returned - {}", costumerFeedback.get());
        return costumerFeedback.get();
    }

    public List<CostumerFeedback> findAllCostumerFeedbacks() {
        info.info("Starting returning all costumer feedbacks");
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
        info.info("Starting delete costumer feedback with id - {}", id);
        try {
            costumerFeedbackRepository.deleteById(id);
            debug.debug("Costumer feedback was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove costumer with id - " + id, e);
            throw new ServiceException("Can't delete costumer feedback with id - "+id);
        }
    }

    public CostumerFeedback createNewCostumerFeedback(CostumerFeedbackDto feedbackDto){
        boolean isExist = costumerService.existsCostumerByEmail(feedbackDto.getCostumerEmail());
        if(!isExist){
            throw new ServiceException("Costumer dose not exist");
        }
        Costumer costumer = costumerService.findCostumerByEmail(feedbackDto.getCostumerEmail());
        CostumerFeedback feedback = new CostumerFeedback();
        feedback.setCostumer(costumer);
        feedback.setCreationDate(new Date().getTime());
        feedback.setFeedback(feedbackDto.getFeedback());
        feedback.setIsChanged(feedbackDto.isChanged());
        feedback.setGrade(feedbackDto.getGrade());
        return feedback;
    }
}
