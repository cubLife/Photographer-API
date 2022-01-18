package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.Costumer;
import com.gmail.serhiiemiv.modeles.Order;
import com.gmail.serhiiemiv.modeles.PhotoSession;
import com.gmail.serhiiemiv.modeles.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private PhotoSessionRepository photoSessionRepository;
    private static final String TEST = "test";

    @Test
    void shouldSaveOrder() {
        generateTestData();
        Order expected = Order.builder().id(1).dateTime(LocalDateTime.of(2022, 01, 7, 16, 45)).build();
        Order actual = orderRepository.findAll().get(0);
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetOrderById() {
        generateTestData();
        int expected = 3;
        int actual = orderRepository.getById(3).getId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllOrders() {
        generateTestData();
        int expected = 5;
        int actual = orderRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteOrderById() {
        generateTestData();
        orderRepository.deleteById(3);
        int expected = 4;
        int actual = orderRepository.findAll().size();
        assertEquals(expected, actual);
    }

    private void generateTestData() {
        User user = new User(TEST, TEST);
        userRepository.save(user);
        Costumer costumer = Costumer.builder().firstName(TEST).lastName(TEST)
                .phone(0).email(TEST).build();
        costumer.setUser(user);
        costumerRepository.save(costumer);
        PhotoSession photoSession = PhotoSession.builder().duration(60).name(TEST).price(0).type(TEST).build();
        photoSessionRepository.save(photoSession);
        for (int i = 0; i < 5; i++) {
            Order order = new Order(LocalDateTime.of(2022, 01, 7, 16, 45), photoSession, costumer);
            orderRepository.save(order);
        }
    }
}