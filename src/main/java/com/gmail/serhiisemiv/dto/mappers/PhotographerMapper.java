package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotographerDto;
import com.gmail.serhiisemiv.modeles.Photographer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotographerMapper {
    private final ModelMapper mapper;

    @Autowired
    public PhotographerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PhotographerDto toDto(Photographer photographer){
        return mapper.map(photographer, PhotographerDto.class);
    }

    public Photographer fromDto(PhotographerDto photographerDto){
        return mapper.map(photographerDto, Photographer.class);
    }
}
