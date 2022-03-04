package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoAlbumDto;
import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoAlbumMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public PhotoAlbumMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PhotoAlbumDto toDto(PhotoAlbum photoAlbum){
        return modelMapper.map(photoAlbum, PhotoAlbumDto.class);
    }

    public List<PhotoAlbumDto> listToDto(List<PhotoAlbum> photos){
        return photos.stream().map(this::toDto).collect(Collectors.toList());
    }
}
