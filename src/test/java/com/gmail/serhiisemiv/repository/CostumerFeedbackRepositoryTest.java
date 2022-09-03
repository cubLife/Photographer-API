package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CostumerFeedbackRepositoryTest {
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
        CostumerFeedback expected = CostumerFeedback.builder().id(1).firstName(TEST).lastName(TEST).feedback(TEST)
                .creationDate(100L)
                .grade(5).build();
        CostumerFeedback actual = costumerFeedbackRepository.findAll().get(0);
        assertEquals(expected, actual);

    }

    @Test
    void shouldFindCostumerFeedbackById() {
        generateTestData();
        CostumerFeedback expected = CostumerFeedback.builder().id(1).firstName(TEST).lastName(TEST).feedback(TEST)
                .creationDate(100L)
                .grade(5).build();
        CostumerFeedback actual = costumerFeedbackRepository.getById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllCostumerFeedbacks() {
        generateTestData();
        int expected = 5;
        int actual = costumerFeedbackRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteCostumerFeedbackById() {
        generateTestData();
        costumerFeedbackRepository.deleteById(3);
        int expected = 4;
        int actual = costumerFeedbackRepository.findAll().size();
        assertEquals(expected, actual);
    }

    private void generateTestData() {
        for (int i = 0; i < 5; i++) {
            CostumerFeedback costumerFeedback = CostumerFeedback.builder().firstName(TEST).lastName(TEST).feedback(TEST)
                    .creationDate(100L)
                    .grade(5).build();
            costumerFeedbackRepository.save(costumerFeedback);
        }
    }
}