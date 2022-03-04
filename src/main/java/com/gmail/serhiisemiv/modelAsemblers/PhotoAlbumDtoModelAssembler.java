package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.PhotoAlbumController;
import com.gmail.serhiisemiv.dto.PhotoAlbumDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotoAlbumDtoModelAssembler implements RepresentationModelAssembler<PhotoAlbumDto, EntityModel<PhotoAlbumDto>> {

    @Override
    public EntityModel<PhotoAlbumDto> toModel(PhotoAlbumDto photoAlbumDto) {
        return EntityModel.of(photoAlbumDto,
               linkTo(methodOn(PhotoAlbumController.class).findById(photoAlbumDto.getId())).withSelfRel(),
                linkTo((methodOn(PhotoAlbumController.class).findAll())).withRel("photoAlbums"));
    }


}
