package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoSessionPackageMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoSessionPackageModelAssembler;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import com.gmail.serhiisemiv.service.PhotoSessionPackageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("api/photo-session-packages")
public class PhotoSessionPackageController {
    private final PhotoSessionPackageService packageService;
    private final PhotoSessionPackageModelAssembler modelAssembler;
    private final PhotoSessionPackageMapper mapper;


    public PhotoSessionPackageController(PhotoSessionPackageService packageService, PhotoSessionPackageModelAssembler modelAssembler, PhotoSessionPackageMapper mapper) {
        this.packageService = packageService;
        this.modelAssembler = modelAssembler;
        this.mapper = mapper;
    }

    @PostMapping()
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    @ResponseStatus(HttpStatus.CREATED)
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
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
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
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public void editPhotoSessionPackageById(@RequestBody PhotoSessionPackageDto sessionPackageDto, @PathVariable int id){
        packageService.editPhotoSessionPackageById(id, sessionPackageDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") int id){
        packageService.deletePhotoSessionPackageById(id);
        return ResponseEntity.noContent().build();
    }

    private PhotoSessionPackage createNewPhotoSessionPackage(PhotoSessionPackageDto photoSessionPackageDto){
        PhotoSessionPackage sessionPackage = new PhotoSessionPackage();
        sessionPackage.setName(photoSessionPackageDto.getName());
        sessionPackage.setDuration(photoSessionPackageDto.getDuration());
        sessionPackage.setNumberPhotos(photoSessionPackageDto.getNumberPhotos());
        sessionPackage.setPrice(photoSessionPackageDto.getPrice());
        return sessionPackage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
