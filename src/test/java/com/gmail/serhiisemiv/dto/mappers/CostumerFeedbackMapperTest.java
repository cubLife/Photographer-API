package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CostumerFeedbackMapperTest {
    @Autowired
    private CostumerFeedbackMapper mapper;
    private static final CostumerFeedback COSTUMER_FEEDBACK = CostumerFeedback.builder().id(1).feedback("Hello World")
            .grade(5).email("gon_doe@gmail.com").firstName("Jon").lastName("Doe").creationDate(15000L).build();

    @Test
    void shouldReturnFeedbackDto() {
        CostumerFeedbackDto expected = CostumerFeedbackDto.builder().id(1).feedback("Hello World")
                .grade(5).email("gon_doe@gmail.com").firstName("Jon").lastName("Doe").creationDate(15000L).build();
        CostumerFeedbackDto actual = mapper.toDto(COSTUMER_FEEDBACK);
        assertEquals(expected,actual);

    }
}