package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.dto.OrderDto;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Order;
import com.gmail.serhiisemiv.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CostumerService costumerService;
    private final PhotoSessionService photoSessionService;
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrderService(OrderRepository orderRepository, CostumerService costumerService, PhotoSessionService photoSessionService) {
        this.orderRepository = orderRepository;
        this.costumerService = costumerService;
        this.photoSessionService = photoSessionService;
    }

    public void saveOrder(Order order) {
        if (order == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new order {}", order);
            orderRepository.save(order);
            debug.debug("Order is saved{}", order);
        } catch (NumberFormatException e) {
            error.error("Cant save order  - " + order, e);
            throw new ServiceException("Cant save order ");
        }
    }

    public Order findOrderById(int id) {
        info.info("Start returned order with id - {}", id);
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            error.error("Order is not present", new ServiceException("Can't find order with id - " + id));
            throw new ServiceException("Can't find order with");
        }
        debug.debug("Order was returned - {}", order.get());
        return order.get();
    }

    public List<Order> findAllOrders() {
        info.info("Starting returning all orders");
        try {
            List<Order> orders = orderRepository.findAll();
            debug.debug("All orders was returned");
            return orders;
        } catch (NullPointerException e) {
            error.error("Can't find any orders- " + e.getMessage(), e);
            throw new ServiceException("Can't find any orders");
        }
    }

    public List<Order> findAllOrdersByCostumerId(int costumerId) {
        info.info("Starting returning all orders by costumer id - {}", costumerId);
        try {
            List<Order> orders = orderRepository.findByCostumer_Id(costumerId);
            debug.debug("All orders was returned by costumer id {} -  was returned", costumerId);
            return orders;
        } catch (NullPointerException e) {
            error.error("Can't find any orders  with costumer id {} ", costumerId, e.fillInStackTrace());
            throw new ServiceException("Can't find any orders");
        }
    }

    public void deleteOrderById(int id) {
        info.info("Starting delete order with id - {}", id);
        try {
            orderRepository.deleteById(id);
            debug.debug("Order was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove order with id - " + id, e);
            throw new ServiceException("Can't delete order with id");
        }
    }

    public Order createNewOrder(OrderDto orderDto){
        Order order = new Order();
        order.setCostumer(costumerService.findCostumerById(orderDto.getCostumerId()));
        order.setPhotoSession(photoSessionService.findPhotoSessionById(orderDto.getPhotoSessionId()));
        order.setCreationDate(new Date().getTime());
        return order;
    }
}
