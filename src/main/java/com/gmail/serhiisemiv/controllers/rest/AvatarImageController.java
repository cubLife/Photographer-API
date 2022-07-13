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
import org.springframework.http.MediaType;
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

    @Autowired
    public AvatarImageController(AvatarImageService avatarImageService, PhotographerService photographerService, AvatarImageMapper mapper) {
        this.avatarImageService = avatarImageService;
        this.photographerService = photographerService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public AvatarImageDto addAvatarImage( @RequestParam MultipartFile file, @RequestParam int photographerId) throws IOException {
        info.info("Starting create new avatarImage");
        Photographer photographer =photographerService.findPhotographerById(photographerId);
        AvatarImage avatarImage = new AvatarImage();
        avatarImage.setPhotographer(photographer);
        avatarImage.setPicture(file.getBytes());
        avatarImageService.addAvatarImage(avatarImage);
        System.out.println(avatarImage);
        AvatarImageDto avatarImageDto = mapper.toDto(avatarImage);
        System.out.println(avatarImageDto);
       return avatarImageDto;
    }

    @GetMapping("/photographer-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly=true)
    @CrossOrigin(origins = {"http://localhost:3000/"})
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
}
