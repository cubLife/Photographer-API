package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.CostumerDto;
import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CostumerMapperTest {
    @Autowired
    CostumerMapper mapper;
    private static final Costumer COSTUMER = Costumer.builder().id(1).firstName("Jon").lastName("Doe").email("jon_doe@gmail.com")
            .phone("+481234567890").build();
    private static final CostumerDto COSTUMER_DTO = CostumerDto.builder().id(1).firstName("Jon").lastName("Doe").email("jon_doe@gmail.com")
            .phone("+481234567890").build();

    @Test
    void shouldReturnCostumerDto() {
        CostumerDto expected = CostumerDto.builder().id(1).firstName("Jon").lastName("Doe").email("jon_doe@gmail.com")
                .phone("+481234567890").build();
        CostumerDto actual = mapper.toDto(COSTUMER);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnListCostumerDto(){
        List<Costumer> costumers = List.of(COSTUMER, COSTUMER, COSTUMER);
        List<CostumerDto> expected = List.of(COSTUMER_DTO, COSTUMER_DTO, COSTUMER_DTO);
        List<CostumerDto> actual = mapper.listToDto(costumers);
        for(int i=0; i<expected.size();i++){
            assertEquals(expected.get(i),actual.get(i));
        }
    }

    @Test
    void shouldReturnCostumer() {
        Costumer expected = COSTUMER;
        Costumer actual = mapper.fromDto(COSTUMER_DTO);
        assertEquals(expected,actual);

    }
}