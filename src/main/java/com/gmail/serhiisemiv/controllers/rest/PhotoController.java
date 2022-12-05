package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.PhotoDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.Image;
import com.gmail.serhiisemiv.modeles.Photo;
import com.gmail.serhiisemiv.service.PhotoAlbumService;
import com.gmail.serhiisemiv.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/photos")
public class PhotoController {
    private final PhotoService photoService;
    private final PhotoAlbumService photoAlbumService;
    private final PhotoDtoModelAssembler photoDtoModelAssembler;
    private final PhotoMapper mapper;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public PhotoController(PhotoService photoService, PhotoAlbumService photoAlbumService, PhotoDtoModelAssembler photoDtoModelAssembler, PhotoMapper mapper) {
        this.photoService = photoService;
        this.photoAlbumService = photoAlbumService;
        this.photoDtoModelAssembler = photoDtoModelAssembler;
        this.mapper = mapper;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoDto savePhoto(@RequestParam MultipartFile file, @RequestParam int photoAlbumId) {
        debug.debug("Starting creating new photo");
        Photo photo = photoService.createNewPhoto(file, photoAlbumId);
        debug.debug("New photo is created");
        photoService.savePhoto(photo);
        return mapper.toDto(photo);
    }

    @PostMapping(value = "/list", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('admin')")
    public List<PhotoDto> saveListPhotos(@RequestParam MultipartFile[] files, @RequestParam int photoAlbumId) {
        debug.debug("Starting creating new photos");
        List<Photo> photos = Arrays.stream(files).map(file -> photoService.createNewPhoto(file, photoAlbumId))
                .collect(Collectors.toList());
        debug.debug("New photos is created");
        photoService.saveAllPhotos(photos);
        return mapper.listToDto(photos);
    }

    @GetMapping(value = "/images/{photo-id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public Resource getImageById(@PathVariable("photo-id") int photoId) {
        Photo photo = photoService.findPhotoById(photoId);
        return new ByteArrayResource(photo.getPicture());
    }

    @GetMapping(value = "/list-images", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public List<Resource> getAllImages() {
        return photoService.findAllPhotos().stream().map(Photo::getPicture).map(ByteArrayResource::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @GetMapping(value = "/{photo-id}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public EntityModel<PhotoDto> findPhotoById(@PathVariable("photo-id") int photoId) {
        Photo photo = photoService.findPhotoById(photoId);
        PhotoDto photoDto = mapper.toDto(photo);
        return photoDtoModelAssembler.toModel(photoDto);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public CollectionModel<EntityModel<PhotoDto>> findAllPhotos() {
        List<Photo> photos = photoService.findAllPhotos();
        photos.sort(Comparator.comparingInt(Image::getId));
        List<PhotoDto> photosDto = mapper.listToDto(photos);
        List<EntityModel<PhotoDto>> entityModels = getEntityModels(photosDto);
        return CollectionModel.of(entityModels, linkTo(methodOn(PhotoController.class).findAllPhotos()).withSelfRel());
    }

    @GetMapping("/photo-album/{photo-album-id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    @CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/"})
    public CollectionModel<EntityModel<PhotoDto>> findAllByPhotoAlbum(@PathVariable("photo-album-id") int albumId) {
        List<Photo> photos = photoService.findAllByAlbumId(albumId);
        photos.sort(Comparator.comparingInt(Image::getId));
        List<PhotoDto> photosDto = mapper.listToDto(photos);
        List<EntityModel<PhotoDto>> entityModels = getEntityModels(photosDto);
        return CollectionModel.of(entityModels, linkTo(methodOn(PhotoController.class).findAllByPhotoAlbum(albumId)).withSelfRel());
    }

    @GetMapping(value = "first-image/photo-album/{photo-album-id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public Resource getFirstImageByAlbumId(@PathVariable("photo-album-id") int albumId) {
        return photoService.findFirstByAlbumId(albumId);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/"})
    @PreAuthorize("hasRole('admin')")
    public void replacePhoto(@RequestBody MultipartFile file, @PathVariable("id") int id) {
        Photo photo = photoService.findPhotoById(id);
        try {
            photo.setName(file.getOriginalFilename());
            photo.setSize(file.getSize());
            photo.setPicture(file.getBytes());
        } catch (IOException e) {
            error.error("Can't create photo. " + e.getMessage(), e);
        }
        debug.debug("Starting save replaced photo");
        photoService.savePhoto(photo);
        debug.debug("Photo is saved");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deletePhotoById(@PathVariable("id") int id) {
        photoService.deletePhotoById(id);
        return ResponseEntity.noContent().build();
    }

    private List<EntityModel<PhotoDto>> getEntityModels(List<PhotoDto> photosDto) {
        return photosDto.stream()
                .map(photoDtoModelAssembler::toModel).collect(Collectors.toList());
    }
}
