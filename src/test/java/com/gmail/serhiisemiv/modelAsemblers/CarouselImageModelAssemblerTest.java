package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.CarouselImageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarouselImageModelAssemblerTest {
    @Autowired
    private CarouselImageModelAssembler modelAssembler;

    @Test
    void toModel() {
        CarouselImageDto expected = new CarouselImageDto(1);
        CarouselImageDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/carousel-images/1>;rel=\"self\",<http://localhost/api/carousel-images/image/1>;rel=\"image\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);
    }
}