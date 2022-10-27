package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.CostumerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CostumerDtoModelAssemblerTest {
    @Autowired
    private CostumerDtoModelAssembler modelAssembler;

    @Test
    void toModel() {
        CostumerDto expected = CostumerDto.builder().id(1).firstName("Jon").lastName("Doe").email("jon_doe@gmail.com")
                .phone("+481234567890").build();
        CostumerDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/costumers/1>;rel=\"self\",<http://localhost/api/costumers/list>;rel=\"Costumers\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);

    }
}