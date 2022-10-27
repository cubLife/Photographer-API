package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.PhotoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoDtoModelAssemblerTest {
    @Autowired
    PhotoDtoModelAssembler modelAssembler;

    @Test
    void toModel() {
        PhotoDto expected = new PhotoDto(1, "Test", 1000L, 5);
        PhotoDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/photos/1>;rel=\"self\",<http://localhost/api/photos/images/1>" +
                ";rel=\"image\",<http://localhost/api/photos/list>;rel=\"photos\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);
    }
}