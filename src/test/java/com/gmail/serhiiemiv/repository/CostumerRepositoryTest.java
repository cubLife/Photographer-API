package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.Costumer;
import com.gmail.serhiiemiv.modeles.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CostumerRepositoryTest {
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private UserRepository userRepository;
    private static final String TEST = "test";

    @Test
    void shouldSavaCostumer(){
        User user = new User(TEST,TEST);
        userRepository.save(user);
        Costumer expected = Costumer.builder().firstName(TEST).lastName(TEST)
                .phone(0).email(TEST).build();
        expected.setUser(user);
        costumerRepository.save(expected);
        Costumer actual =costumerRepository.findAll().get(0);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFIndCostumerById(){
        generateTestData();
        int expected = 3;
        int actual = costumerRepository.getById(3).getId();
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
    void shouldDeleteCostumer(){
        generateTestData();
        costumerRepository.deleteById(3);
        int expected  = 4;
        int actual = costumerRepository.findAll().size();
        assertEquals(expected, actual);
    }

    private void generateTestData(){
        User user = new User(TEST,TEST);
        userRepository.save(user);
        for (int i=0; i<5; i++) {
            Costumer costumer = Costumer.builder().firstName(TEST).lastName(TEST)
                    .phone(0).email(TEST).build();
            costumer.setUser(user);
            costumerRepository.save(costumer);
        }
    }
}