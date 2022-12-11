package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoSessionPackageMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoSessionPackageDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import com.gmail.serhiisemiv.service.PhotoSessionPackageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/photo-session-packages")
@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/", "https://na-photo.pl/", "https://admin.na-photo.pl/"})
public class PhotoSessionPackageController {
    private final PhotoSessionPackageService packageService;
    private final PhotoSessionPackageDtoModelAssembler modelAssembler;
    private final PhotoSessionPackageMapper mapper;

    public PhotoSessionPackageController(PhotoSessionPackageService packageService, PhotoSessionPackageDtoModelAssembler modelAssembler, PhotoSessionPackageMapper mapper) {
        this.packageService = packageService;
        this.modelAssembler = modelAssembler;
        this.mapper = mapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('admin')")
    public PhotoSessionPackageDto savePhotoSession(@RequestBody @Valid PhotoSessionPackageDto photoSessionPackageDto) {
        PhotoSessionPackage photoSessionPackage = createNewPhotoSessionPackage(photoSessionPackageDto);
        packageService.savePhotoSessionPackage(photoSessionPackage);
        return mapper.toDto(photoSessionPackage);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<PhotoSessionPackageDto> getById(@PathVariable("id") int id) {
        PhotoSessionPackage photoSessionPackage = packageService.findPhotoSessionPackageById(id);
        return modelAssembler.toModel(mapper.toDto(photoSessionPackage));
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<PhotoSessionPackageDto>> getAll() {
        List<PhotoSessionPackage> photoSessionPackages = packageService.findAllPhotoSessionPackages();
        List<EntityModel<PhotoSessionPackageDto>> entityModels = getEntityModels(photoSessionPackages);
        return CollectionModel.of(entityModels, linkTo(methodOn(PhotoSessionController.class).getAll()).withSelfRel());
    }

    private List<EntityModel<PhotoSessionPackageDto>> getEntityModels(@NotNull List<PhotoSessionPackage> photoSessionPackages) {
        return photoSessionPackages.stream().map(mapper::toDto).map(modelAssembler::toModel).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('admin')")
    @CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/", "https://na-photo.pl/", "https://admin.na-photo.pl/"})
    public void editPhotoSessionPackageById(@RequestBody PhotoSessionPackageDto sessionPackageDto, @PathVariable int id) {
        packageService.editPhotoSessionPackageById(id, sessionPackageDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") int id) {
        packageService.deletePhotoSessionPackageById(id);
        return ResponseEntity.noContent().build();
    }

    private PhotoSessionPackage createNewPhotoSessionPackage(PhotoSessionPackageDto photoSessionPackageDto) {
        PhotoSessionPackage sessionPackage = new PhotoSessionPackage();
        sessionPackage.setName(photoSessionPackageDto.getName());
        sessionPackage.setDuration(photoSessionPackageDto.getDuration());
        sessionPackage.setNumberPhotos(photoSessionPackageDto.getNumberPhotos());
        sessionPackage.setPrice(photoSessionPackageDto.getPrice());
        return sessionPackage;
    }
}
