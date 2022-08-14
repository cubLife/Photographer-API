package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.dto.OrderDto;
import com.gmail.serhiisemiv.dto.mappers.OrderMapper;
import com.gmail.serhiisemiv.modelAsemblers.OrderModelAssembler;
import com.gmail.serhiisemiv.modeles.Order;
import com.gmail.serhiisemiv.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final OrderModelAssembler modelAssembler;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper mapper, OrderModelAssembler modelAssembler) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.modelAssembler = modelAssembler;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public OrderDto saveOrder(@RequestBody @Valid OrderDto orderDto) {
        Order order = orderService.createNewOrder(orderDto);
        orderService.saveOrder(order);
        return mapper.toDto(order);
    }

    @GetMapping("/{order-id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<OrderDto> getOrderById(@PathVariable("order-id") int orderId) {
        Order order = orderService.findOrderById(orderId);
        return modelAssembler.toModel(mapper.toDto(order));
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = {"http://localhost:3000/","http://localhost:3001/"})
    public CollectionModel<EntityModel<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        List<EntityModel<OrderDto>> orderDtoModels = getEntityModels(orders);
        return CollectionModel.of(orderDtoModels, linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel());
    }

    private List<EntityModel<OrderDto>> getEntityModels(List<Order> orders) {
        return orders.stream().map(mapper::toDto).map(modelAssembler::toModel).collect(Collectors.toList());
    }

    @DeleteMapping("/{order-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable("order-id") int id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}