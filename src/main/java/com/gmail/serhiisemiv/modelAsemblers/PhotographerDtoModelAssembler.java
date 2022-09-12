package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.PhotographerController;
import com.gmail.serhiisemiv.dto.PhotographerDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotographerDtoModelAssembler implements RepresentationModelAssembler<PhotographerDto, EntityModel<PhotographerDto>> {
    @Override
    public @NotNull EntityModel<PhotographerDto> toModel(@NotNull PhotographerDto photographerDto) {
        return EntityModel.of(photographerDto,
                linkTo(methodOn(PhotographerController.class).findById(photographerDto.getId())).withSelfRel());
    }
}
