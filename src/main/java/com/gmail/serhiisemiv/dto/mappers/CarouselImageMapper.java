package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.CarouselImageDto;
import com.gmail.serhiisemiv.modeles.CarouselImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarouselImageMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CarouselImageMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarouselImageDto toDto(CarouselImage carouselImage) {
        return modelMapper.map(carouselImage, CarouselImageDto.class);
    }

    public List<CarouselImageDto> listToDto(List<CarouselImage> carouselImages) {
        return carouselImages.stream().map(this::toDto).collect(Collectors.toList());
    }
}
