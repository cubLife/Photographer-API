package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.CarouselImageDto;
import com.gmail.serhiisemiv.dto.PhotoDto;
import com.gmail.serhiisemiv.dto.mappers.CarouselImageMapper;
import com.gmail.serhiisemiv.modelAsemblers.CarouselImageModelAssembler;
import com.gmail.serhiisemiv.modeles.CarouselImage;
import com.gmail.serhiisemiv.modeles.Photo;
import com.gmail.serhiisemiv.service.CarouselImageService;
import javassist.bytecode.ByteArray;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/carousel-images")
public class CarouselImageController {
    private final CarouselImageService carouselImageService;
    private final CarouselImageModelAssembler modelAssembler;
    private final CarouselImageMapper mapper;
    private final Logger info = LoggerFactory.getLogger(this.getClass());
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CarouselImageController(CarouselImageService carouselImageService, CarouselImageModelAssembler modelAssembler, CarouselImageMapper carouselImageMapper) {
        this.carouselImageService = carouselImageService;
        this.modelAssembler = modelAssembler;
        this.mapper = carouselImageMapper;
    }

    @PostMapping(consumes = {"multipart/form-data"}, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/" })
    public CarouselImageDto saveCarouselImage(@RequestParam MultipartFile file) throws IOException {
        info.info("Starting creating new carousel image");
        CarouselImage carouselImage = carouselImageService.createNewCarouselImage(file);
        info.info("New carousel image is created");
        carouselImageService.addCarouselImage(carouselImage);
        return mapper.toDto(carouselImage);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public EntityModel<CarouselImageDto> getImageById(@PathVariable("id") int id) {
        CarouselImage carouselImage = carouselImageService.findCarouselImageById(id);
        return modelAssembler.toModel(mapper.toDto(carouselImage));
    }

    @GetMapping(value = "/image/{image-id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public Resource getBytesImageById(@PathVariable("image-id") int id) {
        CarouselImage carouselImage = carouselImageService.findCarouselImageById(id);
        return new ByteArrayResource(carouselImage.getPicture());
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public CollectionModel<EntityModel<CarouselImageDto>> findAllCarouselImages() {
        List<CarouselImageDto> carouselImageDto = mapper.listToDto(carouselImageService.findAllCarouselImages());
        List<EntityModel<CarouselImageDto>> entityModels = getEntityModels(carouselImageDto);
        return CollectionModel.of(entityModels, linkTo(methodOn(CarouselImageController.class).findAllCarouselImages()).withSelfRel());
    }

    @PutMapping(value = "/{id}",  consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public void replacePhoto(@RequestBody MultipartFile file, @PathVariable("id") int id){
        CarouselImage carouselImage= carouselImageService.findCarouselImageById(id);
        try {
            carouselImage.setPicture(file.getBytes());
        } catch (IOException e) {
            error.error("Can't edit carousel image. " + e.getMessage(), e);
        }
        debug.debug("Starting save replaced carousel image");
        carouselImageService.addCarouselImage(carouselImage);
        info.info("Carousel image is saved");
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/" })
    public ResponseEntity<HttpStatus> deletePhotoById(@PathVariable("id") int id) {
        carouselImageService.deleteCarouselImageById(id);
        return ResponseEntity.noContent().build();
    }

    private List<EntityModel<CarouselImageDto>> getEntityModels(List<CarouselImageDto> carouselImagesDto) {
        return carouselImagesDto.stream()
                .map(modelAssembler::toModel).collect(Collectors.toList());
    }
}