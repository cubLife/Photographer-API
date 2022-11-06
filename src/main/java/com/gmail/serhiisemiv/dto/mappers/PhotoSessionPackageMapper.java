package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PhotoSessionPackageMapper {
    private final ModelMapper mapper;

    public PhotoSessionPackageMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PhotoSessionPackageDto toDto(PhotoSessionPackage photoSessionPackage) {
        return mapper.map(photoSessionPackage, PhotoSessionPackageDto.class);
    }
}
