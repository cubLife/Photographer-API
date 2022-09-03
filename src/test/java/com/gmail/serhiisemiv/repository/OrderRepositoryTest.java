package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.OrderStatus;
import com.gmail.serhiisemiv.modeles.*;
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
    private PhotoSessionPackageRepository photoSessionPackageRepository;
    private static final String TEST = "test";

    @Test
    void shouldSaveOrder() {
        generateTestData();
        PhotoSessionPackage sessionPackage = new PhotoSessionPackage(1, TEST, 15, 400, 60, null);
        Order expected = Order.builder().id(1).creationDate(100L).startTime(200L).endTime(300L).photoSessionName(TEST).photoSessionPackage(sessionPackage).build();
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
    void shouldFindAllOrdersByCostumerId() {
        generateTestData();
        int expected = 5;
        int actual = orderRepository.findByCostumer_Id(2).size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteOrderById() {
        generateTestData();
        orderRepository.deleteById(3);
        int expected = 9;
        int actual = orderRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByOrderStatus() {
        generateTestData();
        int expected = 5;
        int actual = orderRepository.findByOrderStatus(OrderStatus.APPROVED).size();
        assertEquals(expected, actual);
    }

    private void generateTestData() {
        Costumer firstCostumer = Costumer.builder().firstName(TEST).lastName(TEST).email(TEST).login(TEST).password(TEST).phone("0").build();
        Costumer secondCostumer = Costumer.builder().firstName(TEST).lastName(TEST).email(TEST).login(TEST).password(TEST).phone("1").build();
        costumerRepository.save(firstCostumer);
        costumerRepository.save(secondCostumer);
        PhotoSession photoSession = PhotoSession.builder().name(TEST).build();

        PhotoSessionPackage sessionPackage = new PhotoSessionPackage(TEST, 15, 400, 60);
        photoSessionPackageRepository.save(sessionPackage);
        for (int i = 0; i < 5; i++) {
            orderRepository.save(new Order(100L, 200L,300L, OrderStatus.NEW, TEST, costumerRepository.findById(1).get(), photoSessionPackageRepository.findById(1).get()));
            orderRepository.save(new Order(100L, 200L,300L,OrderStatus.APPROVED, TEST, costumerRepository.findById(2).get(), photoSessionPackageRepository.findById(1).get()));
        }
    }
}