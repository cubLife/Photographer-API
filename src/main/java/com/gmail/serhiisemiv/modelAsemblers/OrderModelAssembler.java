package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.controllers.rest.OrderController;
import com.gmail.serhiisemiv.dto.OrderDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<OrderDto, EntityModel<OrderDto>> {
    @Override
    public EntityModel<OrderDto> toModel(OrderDto orderDto) {
        return EntityModel.of(orderDto,
                linkTo(methodOn(OrderController.class).getOrderById(orderDto.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).getAllOrders()).withRel("allOrders"));
    }
}
