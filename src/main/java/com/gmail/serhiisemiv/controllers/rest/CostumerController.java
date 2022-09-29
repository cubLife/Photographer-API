package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.CostumerDto;
import com.gmail.serhiisemiv.dto.mappers.CostumerMapper;
import com.gmail.serhiisemiv.modelAsemblers.CostumerDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.service.CostumerService;
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

@RestController
@RequestMapping("api/costumers")
public class CostumerController {
    private final CostumerService costumerService;
    private final CostumerDtoModelAssembler modelAssembler;
    private final CostumerMapper mapper;
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CostumerController(CostumerService costumerService, CostumerDtoModelAssembler modelAssembler, CostumerMapper mapper) {
        this.costumerService = costumerService;
        this.modelAssembler = modelAssembler;
        this.mapper = mapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CostumerDto saveCostumer(@RequestBody @Valid CostumerDto costumerDto) {
        info.info("Starting create new costumer {}", costumerDto);
        Costumer costumer = mapper.fromDto(costumerDto);
        info.info("Costumer is created {}", costumer);
        costumerService.saveCostumer(costumer);
        return mapper.toDto(costumer);
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{costumer-id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<CostumerDto> findById(@PathVariable("costumer-id") int costumerId){
       Costumer costumer = costumerService.findCostumerById(costumerId);
        return modelAssembler.toModel(mapper.toDto(costumer));
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<CostumerDto>> findAll(){
        List<CostumerDto> dtoList = mapper.listToDto(costumerService.findAllCostumers());
        List<EntityModel<CostumerDto>> entityModels = dtoList.stream().map(modelAssembler::toModel).collect(Collectors.toList());
       return CollectionModel.of(entityModels, linkTo(methodOn(CostumerController.class).findAll()).withSelfRel());
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deleteCostumerById(@PathVariable int id) {
        costumerService.deleteCostumerById(id);
        return ResponseEntity.noContent().build();
    }
}
