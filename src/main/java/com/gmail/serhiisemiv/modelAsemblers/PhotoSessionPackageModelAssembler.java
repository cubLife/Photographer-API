package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.PhotoSessionController;
import com.gmail.serhiisemiv.controllers.rest.PhotoSessionPackageController;
import com.gmail.serhiisemiv.dto.PhotoSessionDto;
import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotoSessionPackageModelAssembler implements RepresentationModelAssembler<PhotoSessionPackageDto, EntityModel<PhotoSessionPackageDto>> {
    @Override
    public EntityModel<PhotoSessionPackageDto> toModel(PhotoSessionPackageDto photoSessionPackageDto) {
        return EntityModel.of(photoSessionPackageDto,
                linkTo(methodOn(PhotoSessionController.class).getById(photoSessionPackageDto.getId())).withSelfRel(),
                linkTo(methodOn(PhotoSessionPackageController.class).getAll()).withRel("photoSessionPackages"));
    }
}
