package com.gmail.serhiisemiv.controllers.rest;


import com.gmail.serhiisemiv.dto.PhotographerDto;
import com.gmail.serhiisemiv.dto.mappers.PhotographerMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotographerDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.Photographer;
import com.gmail.serhiisemiv.service.PhotographerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("api/photographers")
@Validated
public class PhotographerController {
    private final PhotographerService photographerService;
    private final PhotographerDtoModelAssembler modelAssembler;
    private final PhotographerMapper mapper;
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public PhotographerController(PhotographerService photographerService, PhotographerDtoModelAssembler modelAssembler, PhotographerMapper mapper) {
        this.photographerService = photographerService;
        this.modelAssembler = modelAssembler;
        this.mapper = mapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('admin')")
    public PhotographerDto addPhotographer(@RequestBody PhotographerDto photographerDto) {
        debug.debug("Starting create new photographer {}", photographerDto);
        Photographer photographer = mapper.fromDto(photographerDto);
        debug.debug("Photographer is created {}", photographer);
        photographerService.savePhotographer(photographer);
        return mapper.toDto(photographer);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/"})
    public EntityModel<PhotographerDto> findById(@PathVariable("id") int id) {
        Photographer photographer = photographerService.findPhotographerById(id);
        return modelAssembler.toModel(mapper.toDto(photographer));
    }

    @PutMapping("/{id}/edit-about")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @PreAuthorize("hasRole('admin')")
    @CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/"})
    public void updateAboutMySelf(@RequestParam String aboutMyself, @PathVariable int id) {
        photographerService.updateAboutMyself(aboutMyself, id);
    }

    @PutMapping("/{id}/edit-email")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @PreAuthorize("hasRole('admin')")
    @CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/"})
    public void updateEmail(@RequestParam @Email(message = "Email should be valid. For example - sample@gmail.com") String email, @PathVariable int id) {
        photographerService.updateEmail(email, id);
    }

    @PutMapping("/{id}/edit-phone")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @PreAuthorize("hasRole('admin')")
    @CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/"})
    public void updatePhone(@RequestParam(value = "phone") @Pattern(regexp = "^\\+(?:[0-9]●?){11}[0-9]$", message = "Please type valid phone number. For example +481234567890") String phone, @PathVariable int id) {
        photographerService.updatePhone(phone, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<HttpStatus> deletePhotographerById(@PathVariable int id) {
        photographerService.deletePhotographerById(id);
        return ResponseEntity.noContent().build();
    }
}
