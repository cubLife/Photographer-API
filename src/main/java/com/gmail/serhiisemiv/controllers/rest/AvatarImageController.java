package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.AvatarImageDto;
import com.gmail.serhiisemiv.dto.mappers.AvatarImageMapper;
import com.gmail.serhiisemiv.modeles.AvatarImage;
import com.gmail.serhiisemiv.modeles.Photographer;
import com.gmail.serhiisemiv.service.AvatarImageService;
import com.gmail.serhiisemiv.service.PhotographerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/avatar-images")
public class AvatarImageController {
    private final AvatarImageService avatarImageService;
    private final PhotographerService photographerService;
    private final AvatarImageMapper mapper;
    private final Logger info = LoggerFactory.getLogger(this.getClass());
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AvatarImageController(AvatarImageService avatarImageService, PhotographerService photographerService, AvatarImageMapper mapper) {
        this.avatarImageService = avatarImageService;
        this.photographerService = photographerService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public AvatarImageDto addAvatarImage( @RequestParam MultipartFile file, @RequestParam int photographerId) throws IOException {
        info.info("Starting create new avatarImage");
        Photographer photographer =photographerService.findPhotographerById(photographerId);
        AvatarImage avatarImage = new AvatarImage();
        avatarImage.setPhotographer(photographer);
        avatarImage.setPicture(file.getBytes());
        avatarImageService.saveAvatarImage(avatarImage);
        return mapper.toDto(avatarImage);
    }

    @GetMapping("/photographer-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly=true)
    @CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/"})
    public EntityModel<AvatarImageDto> getAvatarImageByPhotographerId(@PathVariable("id") int id){
        AvatarImage avatarImage = avatarImageService.findByPhotographerId(id);
        return EntityModel.of(mapper.toDto(avatarImage), linkTo(methodOn(AvatarImageController.class).getAvatarImagePicture(id)).withSelfRel());
    }

    @GetMapping("/photographer-id/{id}/picture")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly=true)
    public Resource getAvatarImagePicture(@PathVariable("id") int id){
        AvatarImage avatarImage = avatarImageService.findByPhotographerId(id);
        return new ByteArrayResource(avatarImage.getPicture());
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping(value = "/{id}",  consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void changeAvatar(@RequestBody MultipartFile file, @PathVariable("id") int id){
        AvatarImage avatarImage = avatarImageService.findByPhotographerId(id);
        try {
            avatarImage.setPicture(file.getBytes());
        } catch (IOException e) {
            error.error("Can't replace photo. " + e.getMessage(), e);
        }
        debug.debug("Starting save replaced photo");
        avatarImageService.saveAvatarImage(avatarImage);
        info.info("Photo is saved");
    }
}
