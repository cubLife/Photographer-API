package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.PhotoController;
import com.gmail.serhiisemiv.dto.PhotoDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotoDtoModelAssembler implements RepresentationModelAssembler<PhotoDto, EntityModel<PhotoDto>> {
    @Override
    public EntityModel<PhotoDto> toModel(PhotoDto photoDto) {
        return EntityModel.of(photoDto,
                linkTo(methodOn(PhotoController.class).findPhotoById(photoDto.getId())).withSelfRel(),
                linkTo(methodOn(PhotoController.class).getImageById(photoDto.getId())).withRel("image"),
                linkTo(methodOn(PhotoController.class).findAllPhotos()).withRel("photos"));
    }
}
