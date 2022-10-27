package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.PhotoAlbumDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoAlbumMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoAlbumDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import com.gmail.serhiisemiv.service.PhotoAlbumService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@RequestMapping(value = "api/photo-albums")

public class PhotoAlbumController {
    private final PhotoAlbumService photoAlbumService;
    private final PhotoAlbumDtoModelAssembler modelAssembler;
    private final PhotoAlbumMapper mapper;

    @Autowired
    public PhotoAlbumController(PhotoAlbumService photoAlbumService, PhotoAlbumDtoModelAssembler modelAssembler, PhotoAlbumMapper mapper) {
        this.photoAlbumService = photoAlbumService;
        this.modelAssembler = modelAssembler;
        this.mapper = mapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('admin')")
    public PhotoAlbumDto savePhotoAlbum(@RequestBody @Valid PhotoAlbumDto photoAlbumDto) {
        PhotoAlbum photoAlbum = photoAlbumService.createPhotoAlbum(photoAlbumDto);
        photoAlbumService.savePhotoAlbum(photoAlbum);
        return mapper.toDto(photoAlbum);
    }

    @GetMapping(value = "/{id}")
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<PhotoAlbumDto> findById(@PathVariable int id) {
        PhotoAlbum photoAlbum = photoAlbumService.findPhotoAlbumById(id);
        return modelAssembler.toModel(mapper.toDto(photoAlbum));
    }

    @GetMapping("/list" )
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<PhotoAlbumDto>> findAll() {
        List<PhotoAlbumDto> photoAlbumDtoList = mapper.listToDto(photoAlbumService.findAllPhotoAlbums());
        List<EntityModel<PhotoAlbumDto>> entityModels = getEntityModels(photoAlbumDtoList);
        return CollectionModel.of(entityModels, linkTo(methodOn(PhotoAlbumController.class).findAll()).withSelfRel());
    }

    @GetMapping("/session-id/{id}/list" )
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<PhotoAlbumDto>> findByPhotoSessionId(@PathVariable int id) {
        List<PhotoAlbumDto> photoAlbumDtoList = mapper.listToDto(photoAlbumService.findByPhotoSessionId(id));
        List<EntityModel<PhotoAlbumDto>> entityModels = getEntityModels(photoAlbumDtoList);
        return CollectionModel.of(entityModels, linkTo(methodOn(PhotoAlbumController.class).findAll()).withSelfRel());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id){
        photoAlbumService.deletePhotoAlbumById(id);
        return ResponseEntity.noContent().build();
    }

    @NotNull
    private List<EntityModel<PhotoAlbumDto>> getEntityModels(List<PhotoAlbumDto> photoAlbumDtoList) {
        return photoAlbumDtoList.stream()
                .map(modelAssembler::toModel).collect(Collectors.toList());
    }

}
