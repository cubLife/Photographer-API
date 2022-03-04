package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.CostumerController;
import com.gmail.serhiisemiv.dto.CostumerDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CostumerDtoModelAssembler implements RepresentationModelAssembler<CostumerDto, EntityModel<CostumerDto>> {

    @Override
    public EntityModel<CostumerDto> toModel(CostumerDto costumerDto) {
        return EntityModel.of(costumerDto,
                linkTo(methodOn(CostumerController.class).findById(costumerDto.getId())).withSelfRel(),
                linkTo(methodOn(CostumerController.class).findAll()).withRel("Costumers"));
    }
}
