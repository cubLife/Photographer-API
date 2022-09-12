package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.CarouselImageDto;
import com.gmail.serhiisemiv.modeles.CarouselImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarouselImageMapperTest {
    @Autowired
    private CarouselImageMapper mapper;
    private static final CarouselImage CAROUSEL_IMAGE = new CarouselImage(1,new byte[10]);

    @Test
    void toDto() {
        CarouselImageDto expected = new CarouselImageDto(1);
        CarouselImageDto actual = mapper.toDto(CAROUSEL_IMAGE);
        assertEquals(expected, actual);
    }

    @Test
    void listToDto() {
        CarouselImageDto imageDto = new CarouselImageDto(1);
        List<CarouselImageDto> expected = List.of(imageDto,imageDto,imageDto);
        List<CarouselImageDto> actual = mapper.listToDto(List.of(CAROUSEL_IMAGE, CAROUSEL_IMAGE,CAROUSEL_IMAGE));
        for(int i=0 ; i<expected.size();i++){
            assertEquals(expected.get(i),actual.get(i));
        }
    }
}