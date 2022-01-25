package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.Order;
import com.gmail.serhiiemiv.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            orderRepository.save(order);
        } catch (NumberFormatException e) {
            throw new ServiceException("Cant save costumer order " + order);
        }
    }

    public Order findOrderById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ServiceException("Can't find order with id - " + id);
        }
        return order.get();
    }

    public List<Order> findAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (NullPointerException e) {
            throw new ServiceException("Can't find any orders");
        }
    }

    public void deleteOrderById(int id) {
        try {
            orderRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ServiceException("Can't delete order with id = " + id);
        }
    }
}
