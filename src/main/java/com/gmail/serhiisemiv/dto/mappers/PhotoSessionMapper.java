package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoSessionDto;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PhotoSessionMapper {
    private final ModelMapper mapper;

    public PhotoSessionMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PhotoSessionDto toDto(PhotoSession photoSession){
        return mapper.map(photoSession, PhotoSessionDto.class);
    }
}
