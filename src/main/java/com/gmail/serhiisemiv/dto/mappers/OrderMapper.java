package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.OrderDto;
import com.gmail.serhiisemiv.modeles.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper mapper;


    @Autowired
    public OrderMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public OrderDto toDto(Order order) {
        return mapper.map(order, OrderDto.class);
    }
}
