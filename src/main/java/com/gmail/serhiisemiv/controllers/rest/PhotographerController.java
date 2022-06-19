package com.gmail.serhiisemiv.controllers.rest;


import com.gmail.serhiisemiv.dto.PhotographerDto;
import com.gmail.serhiisemiv.dto.mappers.PhotographerMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotographerModelAssembler;
import com.gmail.serhiisemiv.modeles.Photographer;
import com.gmail.serhiisemiv.service.PhotographerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/photographers")
public class PhotographerController {
    private final PhotographerService photographerService;
    private final PhotographerModelAssembler modelAssembler;
    private final PhotographerMapper mapper;
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PhotographerController(PhotographerService photographerService, PhotographerModelAssembler modelAssembler, PhotographerMapper mapper) {
        this.photographerService = photographerService;
        this.modelAssembler = modelAssembler;
        this.mapper = mapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PhotographerDto addPhotographer(@RequestBody PhotographerDto photographerDto) {
        info.info("Starting create new photographer {}", photographerDto);
        Photographer photographer = mapper.fromDto(photographerDto);
        info.info("Photographer is created {}", photographer);
        photographerService.savePhotographer(photographer);
        return mapper.toDto(photographer);
    }

    @GetMapping("/{photographer-id}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public EntityModel<PhotographerDto> findById(@PathVariable("photographer-id") int id) {
        Photographer photographer = photographerService.findPhotographerById(id);
        return modelAssembler.toModel(mapper.toDto(photographer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deletePhotographerById(@PathVariable int id) {
        photographerService.deletePhotographerById(id);
        return ResponseEntity.noContent().build();
    }
}
