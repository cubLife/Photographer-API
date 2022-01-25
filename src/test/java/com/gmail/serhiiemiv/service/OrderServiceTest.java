package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.Order;
import com.gmail.serhiiemiv.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderService mockOrderService;
    @InjectMocks
    private OrderService orderService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = openMocks(this);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldInvokeSaveOrder() {
        orderService.saveOrder(new Order());
        verify(orderRepository, times(1)).save(new Order());
    }

    @Test
    void shouldInvokeFindOrderById() {
        mockOrderService.findOrderById(anyInt());
        verify(mockOrderService, times(1)).findOrderById(anyInt());
    }

    @Test
    void shouldInvokeFindAllOrders() {
        mockOrderService.findAllOrders();
        verify(mockOrderService, times(1)).findAllOrders();
    }

    @Test
    void shouldInvokeDeleteOrderById() {
        orderService.deleteOrderById(anyInt());
        verify(orderRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> orderService.saveOrder(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockOrderService).saveOrder(new Order());
        assertThrows(ServiceException.class, () -> mockOrderService.saveOrder(new Order()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockOrderService).findOrderById(anyInt());
        assertThrows(ServiceException.class, () -> mockOrderService.findOrderById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockOrderService).findAllOrders();
        assertThrows(ServiceException.class, () -> mockOrderService.findAllOrders());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockOrderService).deleteOrderById(anyInt());
        assertThrows(ServiceException.class, () -> mockOrderService.deleteOrderById(anyInt()));
    }
}