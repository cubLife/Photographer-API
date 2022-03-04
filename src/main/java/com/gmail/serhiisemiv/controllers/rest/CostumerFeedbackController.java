package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import com.gmail.serhiisemiv.dto.mappers.CostumerFeedbackMapper;
import com.gmail.serhiisemiv.modelAsemblers.CostumerFeedbackDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import com.gmail.serhiisemiv.service.CostumerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/feedbacks")
public class CostumerFeedbackController {
    private final CostumerFeedbackService feedbackService;
    private final CostumerFeedbackMapper mapper;
    private final CostumerFeedbackDtoModelAssembler modelAssembler;

    @Autowired
    public CostumerFeedbackController(CostumerFeedbackService feedbackService, CostumerFeedbackMapper mapper, CostumerFeedbackDtoModelAssembler modelAssembler) {
        this.feedbackService = feedbackService;
        this.mapper = mapper;
        this.modelAssembler = modelAssembler;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CostumerFeedbackDto saveCostumerFeedback(@RequestBody CostumerFeedbackDto feedbackDto){
        CostumerFeedback feedback = feedbackService.createNewCostumerFeedback(feedbackDto);
        feedbackService.saveCostumerFeedback(feedback);
        CostumerFeedbackDto dto =mapper.toDto(feedback);
        return dto;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<CostumerFeedbackDto> getById(@PathVariable("id") int id){
        CostumerFeedbackDto feedbackDto = mapper.toDto(feedbackService.findCostumerFeedbackById(id));
        return modelAssembler.toModel(feedbackDto);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<CostumerFeedbackDto>> getAll(){
        List<CostumerFeedback> feedbacks = feedbackService.findAllCostumerFeedbacks();
        List<EntityModel<CostumerFeedbackDto>> entityModels = getModels(feedbacks);
        return CollectionModel.of(entityModels, linkTo(methodOn(CostumerFeedbackController.class).getAll()).withSelfRel());
    }

    @DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") int id){
        feedbackService.deleteCostumerFeedbackById(id);
        return ResponseEntity.noContent().build();
    }

    private List<EntityModel<CostumerFeedbackDto>> getModels(List<CostumerFeedback> feedbacks) {
        return feedbacks.stream().map(mapper::toDto).map(modelAssembler::toModel).collect(Collectors.toList());
    }
}
