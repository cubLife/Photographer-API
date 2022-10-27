package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDtoModelAssemblerTest {
    @Autowired
    private OrderDtoModelAssembler modelAssembler;

    @Test
    void toModel() {
        OrderDto expected = OrderDto.builder().id(1).costumerFirstName("Jon").costumerLastName("Doe")
                .costumerEmail("gon_doe@gmail.com").costumerPhone("+481234567890").orderStatus("New").build();
        OrderDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/orders/1>;rel=\"self\",<http://localhost/api/orders/list>;rel=\"allOrders\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);

    }
}