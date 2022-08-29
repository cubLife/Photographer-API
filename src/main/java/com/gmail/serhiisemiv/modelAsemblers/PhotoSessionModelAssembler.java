package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.PhotoAlbumController;
import com.gmail.serhiisemiv.controllers.rest.PhotoSessionController;
import com.gmail.serhiisemiv.controllers.rest.PhotoSessionIconController;
import com.gmail.serhiisemiv.dto.PhotoSessionDto;
import com.gmail.serhiisemiv.modeles.PhotoSessionIcon;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotoSessionModelAssembler implements RepresentationModelAssembler<PhotoSessionDto, EntityModel<PhotoSessionDto>> {
    @Override
    public EntityModel<PhotoSessionDto> toModel(PhotoSessionDto photoSessionDto) {
        return EntityModel.of(photoSessionDto,
                linkTo(methodOn(PhotoSessionController.class).getById(photoSessionDto.getId())).withSelfRel(),
                linkTo(methodOn(PhotoSessionController.class).getAll()).withRel("photoSessions"),
        linkTo(methodOn(PhotoSessionIconController.class).getPictureByPhotoSessionId(photoSessionDto.getId())).withRel("icon"),
        linkTo(methodOn(PhotoAlbumController.class).findByPhotoSessionId(photoSessionDto.getId())).withRel("photoAlbums")) ;
    }
}
