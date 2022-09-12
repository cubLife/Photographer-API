package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoSessionIconDto;
import com.gmail.serhiisemiv.modeles.PhotoSessionIcon;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class PhotoSessionIconMapper {

    private final ModelMapper modelMapper;

    public PhotoSessionIconMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        TypeMap<PhotoSessionIcon, PhotoSessionIconDto> propertyMapperToDto = modelMapper.createTypeMap(PhotoSessionIcon.class, PhotoSessionIconDto.class);

        propertyMapperToDto.addMappings(mapper -> mapper.map(PhotoSessionIcon::getId, PhotoSessionIconDto::setId));
        propertyMapperToDto.addMappings(mapper -> mapper.map(photoSessionIcon -> photoSessionIcon.getPhotoSession().getId(),PhotoSessionIconDto::setPhotoSessionId));
    }

    public PhotoSessionIconDto toDto(PhotoSessionIcon sessionIcon) {
        return modelMapper.map(sessionIcon, PhotoSessionIconDto.class);
    }

}
