package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.PhotoDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoDtoModelAssembler;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/photos")
//@CrossOrigin(origins = {"http://localhost:3000/"})
public class PhotoController {
    private final PhotoService photoService;
    private final PhotoAlbumService photoAlbumService;
    private final PhotoDtoModelAssembler photoDtoModelAssembler;
    private final PhotoMapper mapper;
    private final Logger info = LoggerFactory.getLogger(this.getClass());
    private final Logger error = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PhotoController(PhotoService photoService, PhotoAlbumService photoAlbumService, PhotoDtoModelAssembler photoDtoModelAssembler, PhotoMapper mapper) {
        this.photoService = photoService;
        this.photoAlbumService = photoAlbumService;
        this.photoDtoModelAssembler = photoDtoModelAssembler;
        this.mapper = mapper;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoDto savePhoto(@RequestParam MultipartFile file, @RequestParam int photoAlbumId) {
        info.info("Starting creating new photo");
        Photo photo = photoService.createNewPhoto(file);
        photo.setPhotoAlbum(photoAlbumService.findPhotoAlbumById(photoAlbumId));
        info.info("New photo is created");
        photoService.savePhoto(photo);
        return mapper.toDto(photo);
    }

    @PostMapping(value = "list", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public List<PhotoDto> saveAllPhotos( @RequestParam MultipartFile[] files) {
        info.info("Starting creating new photos");
        List<Photo> photos = Arrays.stream(files).map(photoService::createNewPhoto)
                .collect(Collectors.toList());
        info.info("New photos is created");
        photoService.saveAllPhotos(photos);
        return mapper.listToDto(photos);
    }

    @GetMapping(value = "/images/{photo-id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public Resource getImageById(@PathVariable("photo-id") int photoId) {
        Photo photo = photoService.findPhotoById(photoId);
        return new ByteArrayResource(photo.getImage());
    }

    @GetMapping(value = "/list-images", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public List<Resource> getAllImages() {
        return photoService.findAllPhotos().stream().map(Photo::getImage).map(ByteArrayResource::new)
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
        List<PhotoDto> photosDto = mapper.listToDto(photoService.findAllPhotos());
        List<EntityModel<PhotoDto>> entityModels = getEntityModels(photosDto);
        return CollectionModel.of(entityModels, linkTo(methodOn(PhotoController.class).findAllPhotos()).withSelfRel());
    }

    @GetMapping("/photo-album/{photo-album-id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly=true)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public CollectionModel<EntityModel<PhotoDto>> findAllByPhotoAlbum(@PathVariable("photo-album-id") int albumId){
        List<PhotoDto> photosDto = mapper.listToDto(photoService.findAllByAlbumId(albumId));
        List<EntityModel<PhotoDto>> entityModels = getEntityModels(photosDto);
        return CollectionModel.of(entityModels, linkTo(methodOn(PhotoController.class).findAllByPhotoAlbum(albumId)).withSelfRel());
    }

    @GetMapping(value = "first-image/photo-album/{photo-album-id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly=true)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public Resource getFirstImageByAlbumId(@PathVariable("photo-album-id") int albumId){
        return photoService.findFirstByAlbumId(albumId);
    }

    @DeleteMapping("/{id}")
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
