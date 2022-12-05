package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.PhotoSessionIconDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoSessionIconMapper;
import com.gmail.serhiisemiv.modeles.PhotoSessionIcon;
import com.gmail.serhiisemiv.service.PhotoSessionIconService;
import com.gmail.serhiisemiv.service.PhotoSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RequestMapping(value = "api/photo-session-icons")
public class PhotoSessionIconController {
    private final PhotoSessionIconService iconService;
    private final PhotoSessionService sessionService;
    private final PhotoSessionIconMapper mapper;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    public PhotoSessionIconController(PhotoSessionIconService iconService, PhotoSessionService sessionService, PhotoSessionIconMapper mapper) {
        this.iconService = iconService;
        this.sessionService = sessionService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('admin')")
    public PhotoSessionIconDto addAPhotoSessionIcon(@RequestParam MultipartFile file, @RequestParam int sessionId) {
        PhotoSessionIcon sessionIcon = null;
        try {
            sessionIcon = iconService.createNew(file, sessionId);
        } catch (IOException e) {
            error.error("Can't create new Photo Session Icon - {}", e.getMessage());
        }
        iconService.save(sessionIcon);
        return mapper.toDto(sessionIcon);
    }

    @GetMapping("/session-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public EntityModel<PhotoSessionIconDto> getByPhotoSessionId(@PathVariable("id") int id) {
        PhotoSessionIcon sessionIcon = iconService.findByPhotoSessionId(id);
        return EntityModel.of(mapper.toDto(sessionIcon), linkTo(methodOn(PhotoSessionIconController.class).getPictureByPhotoSessionId(id)).withSelfRel());
    }

    @GetMapping("/session-id/{id}/icon")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public Resource getPictureByPhotoSessionId(@PathVariable("id") int id) {
        PhotoSessionIcon sessionIcon = iconService.findByPhotoSessionId(id);
        return new ByteArrayResource(sessionIcon.getPicture());
    }

    @PutMapping(value = "/session-id/{id}/icon", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('admin')")
    @Transactional
    public void changeIcon(@RequestBody MultipartFile file, @PathVariable("id") int id) {
        PhotoSessionIcon sessionIcon = iconService.findByPhotoSessionId(id);
        try {
            sessionIcon.setPicture(file.getBytes());
        } catch (IOException e) {
            error.error("Can't replace photo session icon. " + e.getMessage(), e);
        }
        debug.debug("Starting save replaced photo session icon");
        iconService.save(sessionIcon);
        debug.debug("Photo session icon replaced");
    }
}
