package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotographerDto;
import com.gmail.serhiisemiv.modeles.Photographer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotographerMapperTest {
    @Autowired
    private PhotographerMapper mapper;
    private static final Photographer PHOTOGRAPHER = Photographer.builder().id(1).firstName("Jon").lastName("Doe")
            .email("jon_doe@gmail.com").phone("+481234567890").aboutMyself("Hi").build();
    private static final PhotographerDto PHOTOGRAPHER_DTO =PhotographerDto.builder().id(1).firstName("Jon").lastName("Doe")
            .email("jon_doe@gmail.com").phone("+481234567890").aboutMyself("Hi").build();

    @Test
    void shouldReturnPhotographerDto() {
        PhotographerDto actual = mapper.toDto(PHOTOGRAPHER);
        assertEquals(PHOTOGRAPHER_DTO, actual);
    }

    @Test
    void fromDto() {
        Photographer actual = mapper.fromDto(PHOTOGRAPHER_DTO);
        assertEquals(PHOTOGRAPHER, actual);
    }
}