package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.PhotoSessionPackageController;
import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotoSessionPackageDtoModelAssembler implements RepresentationModelAssembler<PhotoSessionPackageDto, EntityModel<PhotoSessionPackageDto>> {
    @Override
    public @NotNull EntityModel<PhotoSessionPackageDto> toModel(@NotNull PhotoSessionPackageDto photoSessionPackageDto) {
        return EntityModel.of(photoSessionPackageDto,
                linkTo(methodOn(PhotoSessionPackageController.class).getById(photoSessionPackageDto.getId())).withSelfRel(),
                linkTo(methodOn(PhotoSessionPackageController.class).getAll()).withRel("photoSessionPackages"));
    }
}
