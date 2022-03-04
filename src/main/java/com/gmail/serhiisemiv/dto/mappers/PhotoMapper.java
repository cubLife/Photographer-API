package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoDto;
import com.gmail.serhiisemiv.modeles.Photo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public PhotoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PhotoDto toDto(Photo photo){
        return modelMapper.map(photo,PhotoDto.class);
    }

    public List<PhotoDto>  listToDto(List<Photo> photos){
        return photos.stream().map(this::toDto).collect(Collectors.toList());
    }
}
