package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.PhotographerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotographerDtoModelAssemblerTest {
    @Autowired
    private PhotographerDtoModelAssembler modelAssembler;

    @Test
    void toModel() {
        PhotographerDto expected = PhotographerDto.builder().id(1).firstName("Jon").lastName("Doe").email("jon_doe@gmail.com")
                .phone("+480123456789").aboutMyself("Hello World").build();
        PhotographerDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/photographers/1>;rel=\"self\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);

    }
}