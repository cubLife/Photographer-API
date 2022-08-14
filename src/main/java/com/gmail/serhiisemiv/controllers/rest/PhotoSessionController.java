package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.PhotoSessionDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoSessionMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoSessionModelAssembler;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import com.gmail.serhiisemiv.service.PhotoSessionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/photo-sessions")
public class PhotoSessionController {
    private final PhotoSessionService photoSessionService;
    private final PhotoSessionMapper mapper;
    private final PhotoSessionModelAssembler modelAssembler;

    @Autowired
    public PhotoSessionController(PhotoSessionService photoSessionService, PhotoSessionMapper mapper, PhotoSessionModelAssembler modelAssembler) {
        this.photoSessionService = photoSessionService;
        this.mapper = mapper;
        this.modelAssembler = modelAssembler;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public PhotoSessionDto savePhotoSession(@RequestBody @Valid PhotoSessionDto photoSessionDto) {
        PhotoSession photoSession = photoSessionService.createNewPhotoSession(photoSessionDto);
        photoSessionService.savePhotoSession(photoSession);
        return mapper.toDto(photoSession);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<PhotoSessionDto> getById(@PathVariable("id") int id) {
        PhotoSession photoSession = photoSessionService.findPhotoSessionById(id);
        return modelAssembler.toModel(mapper.toDto(photoSession));
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public CollectionModel<EntityModel<PhotoSessionDto>> getAll() {
        List<PhotoSession> photoSessions = photoSessionService.findAllPhotoSessions();
        List<EntityModel<PhotoSessionDto>> entityModels = getEntityModels(photoSessions);
        return CollectionModel.of(entityModels, linkTo(methodOn(PhotoSessionController.class).getAll()).withRel("list"));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") int id){
        photoSessionService.deletePhotoSessionById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    @ResponseStatus(HttpStatus.OK)
    public void editPhotoSession(@RequestBody PhotoSessionDto photoSessionDto, @PathVariable int id){
        photoSessionService.editPhotoSession(id, photoSessionDto);
    }

    private List<EntityModel<PhotoSessionDto>> getEntityModels(@NotNull List<PhotoSession> photoSessions) {
        return photoSessions.stream().map(mapper::toDto).map(modelAssembler::toModel).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
