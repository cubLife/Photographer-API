package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.CostumerDto;
import com.gmail.serhiisemiv.modeles.Costumer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CostumerMapper {
    private final ModelMapper mapper;

    @Autowired
    public CostumerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CostumerDto toDto(Costumer costumer){
        return mapper.map(costumer, CostumerDto.class);
    }

    public List<CostumerDto> listToDto(List<Costumer> costumers){
        return costumers.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Costumer fromDto(CostumerDto costumerDto){
        return mapper.map(costumerDto, Costumer.class);
    }
}
