package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.AvatarImageDto;
import com.gmail.serhiisemiv.modeles.AvatarImage;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvatarImageMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public AvatarImageMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        TypeMap<AvatarImage, AvatarImageDto> propertyMapperToDto = modelMapper.createTypeMap(AvatarImage.class, AvatarImageDto.class);

        propertyMapperToDto.addMappings(mapper -> mapper.map(AvatarImage::getId, AvatarImageDto::setId));
        propertyMapperToDto.addMappings(mapper -> mapper.map(avatarImage -> avatarImage.getPhotographer().getId(),AvatarImageDto::setPhotographerId));
    }

    public AvatarImageDto toDto(AvatarImage avatarImage) {
        return modelMapper.map(avatarImage, AvatarImageDto.class);
    }
}
