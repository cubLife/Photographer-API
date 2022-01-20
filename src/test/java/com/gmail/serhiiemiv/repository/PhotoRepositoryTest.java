package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PhotoRepositoryTest {
    @Autowired
    PhotoRepository photoRepository;
    private static final String TEST = "test";

    @Test
    void shouldSavePhoto() {
        photoRepository.save(new Photo(TEST, 1L, new byte[0]));
        Photo expected = Photo.builder().id(1).name(TEST).size(1L).image(new byte[0]).build();
        Photo actual = photoRepository.getById(1);
        assertEquals(expected, actual);

    }

    @Test
    void shouldFindPhotoById() {
        generateTestDAta();
        Photo expected = Photo.builder().id(3).name(TEST).size(1L).image(new byte[3]).build();
        Photo actual = photoRepository.getById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllPhotos() {
        generateTestDAta();
        int expected = 5;
        int actual = photoRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeletePhotoById() {
        generateTestDAta();
        photoRepository.deleteById(1);
        int expected = 4;
        int actual = photoRepository.findAll().size();
        assertEquals(expected, actual);
    }

    private void generateTestDAta() {
        for (int i = 0; i < 5; i++) {
            photoRepository.save(new Photo(TEST, 1L, new byte[3]));
        }
    }
}