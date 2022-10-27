package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Costumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(value = "test")
class CostumerRepositoryTest {
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private UserRepository userRepository;
    private static final String TEST = "test@dmail.com";

    @Test
    void shouldSavaCostumer(){
        Costumer expected = Costumer.builder().firstName(TEST).lastName(TEST)
                .phone("0").email(TEST).build();
        costumerRepository.save(expected);
        Costumer actual =costumerRepository.findAll().get(0);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFIndCostumerById(){
        generateTestData();
        Optional<Costumer> costumer = costumerRepository.findById(3);
        assertTrue(costumer.isPresent());
        int expected = 3;
        int actual = costumer.get().getId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllCostumers(){
        generateTestData();
        int expected = 5;
        int actual = costumerRepository.findAll().size();
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnTrueExistCostumerByEmail() {
        generateTestData();
        boolean expected = true;
        boolean actual = costumerRepository.existsCostumerByEmail(TEST);
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnFalseExistCostumerByEmail() {
        generateTestData();
        boolean expected = false;
        boolean actual = costumerRepository.existsCostumerByEmail("costumer@gmail.com");
        assertEquals(expected,actual);
    }

    @Test
    void shouldDeleteCostumer(){
        generateTestData();
        costumerRepository.deleteById(3);
        int expected  = 4;
        int actual = costumerRepository.findAll().size();
        assertEquals(expected, actual);
    }

    private void generateTestData(){
        for (int i=0; i<5; i++) {
            Costumer costumer = Costumer.builder().firstName(TEST).lastName(TEST)
                    .phone("0").email(TEST).build();
            costumerRepository.save(costumer);
        }
    }
}