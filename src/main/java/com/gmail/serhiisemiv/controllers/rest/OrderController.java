package com.gmail.serhiisemiv.controllers.rest;

import com.gmail.serhiisemiv.OrderStatus;
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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/", "http://localhost:3001/"})
@RequestMapping(value = "api/orders")
@Transactional
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
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        List<EntityModel<OrderDto>> orderDtoModels = getEntityModels(orders);
        return CollectionModel.of(orderDtoModels, linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel());
    }

    @GetMapping("/order-status/{status}/list")

    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<OrderDto>> getByOrderStatus(@PathVariable(value = "status") String status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        List<Order> orders = orderService.findByOrderStatus(orderStatus);
        List<EntityModel<OrderDto>> orderDtoModels = getEntityModels(orders);
        return CollectionModel.of(orderDtoModels, linkTo(methodOn(OrderController.class).getByOrderStatus(status)).withSelfRel());
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editOrder(@RequestBody OrderDto orderDto, @PathVariable int id) {
        orderService.editOrder(orderDto, id);

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