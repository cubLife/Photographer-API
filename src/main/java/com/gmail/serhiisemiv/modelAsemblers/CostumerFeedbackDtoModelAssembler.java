package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.CostumerFeedbackController;
import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CostumerFeedbackDtoModelAssembler implements RepresentationModelAssembler<CostumerFeedbackDto, EntityModel<CostumerFeedbackDto>> {
    @Override
    public EntityModel<CostumerFeedbackDto> toModel(CostumerFeedbackDto feedbackDto) {
        return EntityModel.of(feedbackDto,
                linkTo(methodOn(CostumerFeedbackController.class).getById(feedbackDto.getId())).withSelfRel(),
       linkTo(methodOn(CostumerFeedbackController.class).getAll()).withRel("feedbacks"));
    }
}
