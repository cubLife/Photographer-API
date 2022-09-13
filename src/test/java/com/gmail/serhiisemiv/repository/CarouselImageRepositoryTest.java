package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.CarouselImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(value = "test")
class CarouselImageRepositoryTest {

    @Autowired
    private CarouselImageRepository repository;

    @Test
    void shouldSaveAvatarImage() {
        createTestData();
        CarouselImage expected = new CarouselImage(1, new byte[5]);
        CarouselImage actual = repository.getById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        createTestData();
        int expected = 1;
        int actual = repository.findById(1).get().getId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllAvatarImages() {
        createTestData();
        int expected = 5;
        int actual = repository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() {
        createTestData();
        repository.deleteById(1);
        int expected = 4;
        int actual = repository.findAll().size();
        assertEquals(expected, actual);
    }


    private void createTestData() {
        for (int i = 0; i < 5; i++) {
            repository.save(new CarouselImage(new byte[5]));
        }
    }
}