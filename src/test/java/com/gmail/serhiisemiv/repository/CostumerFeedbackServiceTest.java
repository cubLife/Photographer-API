package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import com.gmail.serhiisemiv.modeles.Grade;
import com.gmail.serhiisemiv.modeles.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CostumerFeedbackServiceTest {
    @Autowired
    private CostumerFeedbackRepository costumerFeedbackRepository;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private UserRepository userRepository;
    private static final String TEST = "test";

    @Test
    void shouldSaveCostumerFeedback() {
        generateTestData();
        CostumerFeedback expected = CostumerFeedback.builder().id(1).feedback(TEST)
                .creationDate(100L)
                .grade(Grade.POSITIVE).isChanged(false).build();
        CostumerFeedback actual = costumerFeedbackRepository.findAll().get(0);
        assertEquals(expected, actual);

    }

    @Test
    void shouldFindCostumerFidbackById() {
        generateTestData();
        CostumerFeedback expected = CostumerFeedback.builder().id(1).feedback(TEST)
                .creationDate(100L)
                .grade(Grade.POSITIVE).isChanged(false).build();
        CostumerFeedback acrual = costumerFeedbackRepository.getById(1);
        assertEquals(expected, acrual);
    }

    @Test
    void shouldFindAllCostumerFitbacks() {
        generateTestData();
        int expected = 5;
        int actual = costumerFeedbackRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteCostumerFitbackById() {
        generateTestData();
        costumerFeedbackRepository.deleteById(3);
        int expected = 4;
        int actual = costumerFeedbackRepository.findAll().size();
    }

    private void generateTestData() {
        User user = new User(TEST, TEST);
        userRepository.save(user);
        Costumer costumer = Costumer.builder().firstName(TEST).lastName(TEST)
                .phone(0).email(TEST).build();
        costumer.setUser(user);
        costumerRepository.save(costumer);
        for (int i = 0; i < 5; i++) {
            CostumerFeedback costumerFeedback = CostumerFeedback.builder().feedback(TEST)
                    .creationDate(100L)
                    .costumer(costumer).grade(Grade.POSITIVE).isChanged(false).build();
            costumerFeedbackRepository.save(costumerFeedback);
        }
    }
}