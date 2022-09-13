package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.OrderStatus;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.modeles.Order;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import com.gmail.serhiisemiv.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles(value = "test")
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderService mockOrderService;
    @InjectMocks
    private OrderService orderService;
    @Autowired
    private CostumerService costumerService;
    @Autowired
    private PhotoSessionPackageService packageService;
    private AutoCloseable autoCloseable;
    private static String TEST = "Test";

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
    void shouldInvokeFindAllOrdersByCostumerId() {
        mockOrderService.findAllOrdersByCostumerId(anyInt());
        verify(mockOrderService, times(1)).findAllOrdersByCostumerId(anyInt());
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
    void shouldThrowServiceExceptionWhenFindAllOrdersByCostumerIdMethodCAll() {
        doThrow(ServiceException.class).when(mockOrderService).findAllOrdersByCostumerId(anyInt());
        assertThrows(ServiceException.class, () -> mockOrderService.findAllOrdersByCostumerId(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockOrderService).deleteOrderById(anyInt());
        assertThrows(ServiceException.class, () -> mockOrderService.deleteOrderById(anyInt()));
    }
}