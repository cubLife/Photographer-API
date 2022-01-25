package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.Costumer;
import com.gmail.serhiiemiv.modeles.CostumerFeedback;
import com.gmail.serhiiemiv.modeles.Grade;
import com.gmail.serhiiemiv.modeles.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
                .dateTime(LocalDateTime.of(2022, 01, 6, 22, 46))
                .grade(Grade.POSITIVE).isChanged(false).build();
        CostumerFeedback actual = costumerFeedbackRepository.findAll().get(0);
        assertEquals(expected, actual);

    }

    @Test
    void shouldFindCostumerFidbackById() {
        generateTestData();
        CostumerFeedback expected = CostumerFeedback.builder().id(1).feedback(TEST)
                .dateTime(LocalDateTime.of(2022, 01, 6, 22, 46))
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
                    .dateTime(LocalDateTime.of(2022, 01, 6, 22, 46))
                    .costumer(costumer).grade(Grade.POSITIVE).isChanged(false).build();
            costumerFeedbackRepository.save(costumerFeedback);
        }
    }
}