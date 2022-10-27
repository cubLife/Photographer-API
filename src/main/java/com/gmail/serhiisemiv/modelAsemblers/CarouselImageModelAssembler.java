package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.CarouselImageController;
import com.gmail.serhiisemiv.dto.CarouselImageDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarouselImageModelAssembler implements RepresentationModelAssembler<CarouselImageDto, EntityModel<CarouselImageDto>> {


    @Override
    public @NotNull EntityModel<CarouselImageDto> toModel(@NotNull CarouselImageDto carouselImageDto) {
        return EntityModel.of(carouselImageDto,
                linkTo(methodOn(CarouselImageController.class).getImageById(carouselImageDto.getId())).withSelfRel()
        ,linkTo(methodOn(CarouselImageController.class).getBytesImageById(carouselImageDto.getId())).withRel("image"));
    }
}