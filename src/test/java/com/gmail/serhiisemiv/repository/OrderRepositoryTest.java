package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.modeles.Order;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import com.gmail.serhiisemiv.modeles.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

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
        Order expected = Order.builder().id(1).creationDate(100L).build();
        Order actual = orderRepository.findAll().get(0);
        System.out.println(actual);
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
        int expected = 10;
        int actual = orderRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllOrdersByCostumerId(){
        generateTestData();
        int expected = 5;
        int actual = orderRepository.findByCostumer_Id(2).size();
        assertEquals(expected,actual);
    }

    @Test
    void shouldDeleteOrderById() {
        generateTestData();
        orderRepository.deleteById(3);
        int expected = 9;
        int actual = orderRepository.findAll().size();
        assertEquals(expected, actual);
    }

    private void generateTestData() {
        User user = new User(TEST, TEST);
        userRepository.save(user);
        costumerRepository.save( Costumer.builder().firstName(TEST).lastName(TEST)
                .phone(0).email(TEST).user(user).build());
        costumerRepository.save( Costumer.builder().firstName(TEST).lastName(TEST)
                .phone(0).email(TEST).user(user).build());
        PhotoSession photoSession = PhotoSession.builder().duration(60).name(TEST).price(0).type(TEST).build();
        photoSessionRepository.save(photoSession);
        for (int i = 0; i < 5; i++) {
            orderRepository.save(new Order(100L, photoSession, costumerRepository.findById(1).get()));
            orderRepository.save(new Order(100L, photoSession, costumerRepository.findById(2).get()));
        }
    }
}