package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CostumerFeedbackDtoModelAssemblerTest {
    @Autowired
    private CostumerFeedbackDtoModelAssembler modelAssembler;

    @Test
    void toModel() {
        CostumerFeedbackDto expected = CostumerFeedbackDto.builder().id(1).firstName("Jon").lastName("Doe")
                .email("gon_doe@gmail.com").feedback("Test").build();
        CostumerFeedbackDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/feedbacks/1>;rel=\"self\",<http://localhost/api/feedbacks/list>;rel=\"feedbacks\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);
    }
}