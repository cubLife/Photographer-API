package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.modeles.OrderStatus;
import com.gmail.serhiisemiv.dto.OrderDto;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.modeles.Order;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
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
    private final PhotoSessionPackageService packageService;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public OrderService(OrderRepository orderRepository, CostumerService costumerService, PhotoSessionPackageService packageService) {
        this.orderRepository = orderRepository;
        this.costumerService = costumerService;
        this.packageService = packageService;
    }

    public void saveOrder(Order order) {
        if (order == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            debug.debug("Start saving new order {}", order);
            orderRepository.save(order);
            debug.debug("Order is saved{}", order);
        } catch (NumberFormatException e) {
            error.error("Cant save order  - " + order, e);
            throw new ServiceException("Cant save order ");
        }
    }

    public Order findOrderById(int id) {
        debug.debug("Start returned order with id - {}", id);
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            error.error("Order is not present", new ServiceException("Can't find order with id - " + id));
            throw new ServiceException("Can't find order with");
        }
        debug.debug("Order was returned - {}", order.get());
        return order.get();
    }

    public List<Order> findAllOrders() {
        debug.debug("Starting returning all orders");
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
        debug.debug("Starting returning all orders by costumer id - {}", costumerId);
        try {
            List<Order> orders = orderRepository.findByCostumer_Id(costumerId);
            debug.debug("All orders was returned by costumer id {} -  was returned", costumerId);
            return orders;
        } catch (NullPointerException e) {
            error.error("Can't find any orders  with costumer id {} ", costumerId, e.fillInStackTrace());
            throw new ServiceException("Can't find any orders");
        }
    }

    public List<Order> findByOrderStatus(OrderStatus status){
        debug.debug("Start returning all orders by order status - {}", status.getStatus());
        try {
            List<Order> orders = orderRepository.findByOrderStatus(status);
            debug.debug("All orders was returned order status was returned order status - {}", status.getStatus());
            return orders;
        } catch (NullPointerException e) {
            error.error("Can't find any orders  with order status  {} ", status.getStatus());
            throw new ServiceException("Can't find any orders  with order status", e);
        }
    }

    public void editOrder(OrderDto orderDto, int id) {
        Order order = this.findOrderById(id);
        debug.debug("Starting edit Order with id - {}", id);
        edit(order, orderDto);
        this.saveOrder(order);
        debug.debug("Order with id edited - {}", order);

    }

    public void deleteOrderById(int id) {
        debug.debug("Starting delete order with id - {}", id);
        try {
            orderRepository.deleteById(id);
            debug.debug("Order was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove order with id - " + id, e);
            throw new ServiceException("Can't delete order with id");
        }
    }

    public Order createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        Costumer costumer;
        PhotoSessionPackage photoSessionPackage = packageService.findPhotoSessionPackageById(orderDto.getPhotoSessionPackageId());
        boolean isExist = costumerService.existsCostumerByEmail(orderDto.getCostumerEmail());
        if (!isExist) {
            costumer = createNewCostumer(orderDto);
            costumerService.saveCostumer(costumer);
        } else {
            costumer = costumerService.findCostumerByEmail(orderDto.getCostumerEmail());
        }
        order.setCostumer(costumer);
        order.setPhotoSessionName(orderDto.getPhotoSessionName());
        order.setPhotoSessionPackage(photoSessionPackage);
        order.setCreationDate(new Date().getTime());
        order.setOrderStatus(OrderStatus.NEW);
        return order;
    }

    private Costumer createNewCostumer(OrderDto orderDto) {
        Costumer costumer = new Costumer();
        costumer.setFirstName(orderDto.getCostumerFirstName());
        costumer.setLastName(orderDto.getCostumerLastName());
        costumer.setEmail(orderDto.getCostumerEmail());
        costumer.setPhone(orderDto.getCostumerPhone());
        return costumer;
    }

    private void edit(Order order, OrderDto orderDto) {
        if (orderDto.getOrderStatus() != null && !orderDto.getOrderStatus().isEmpty()) {
            order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));
        }
        if (orderDto.getStartTime() > 0) {
            order.setStartTime(orderDto.getStartTime());
        }
        if (orderDto.getEndTime() > 0) {
            order.setEndTime(orderDto.getEndTime());
        }
        if (orderDto.getPhotoSessionName() != null && !orderDto.getPhotoSessionName().isEmpty() ) {
            order.setPhotoSessionName(orderDto.getPhotoSessionName());
        }
        if (orderDto.getPhotoSessionPackageId() > 0) {
            order.setPhotoSessionPackage(packageService.findPhotoSessionPackageById(orderDto.getPhotoSessionPackageId()));
        }
    }
}
