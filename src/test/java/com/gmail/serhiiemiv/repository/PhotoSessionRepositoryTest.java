package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.PhotoSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PhotoSessionRepositoryTest {
    private static final String TEST = "test";
    @Autowired
    PhotoSessionRepository photoSessionRepository;

    @Test
    void shouldSavePhotoSession() {
        generateTestData();
        PhotoSession expected = PhotoSession.builder().id(1).duration(60).name(TEST).price(0).type(TEST).build();
        PhotoSession actual = photoSessionRepository.findAll().get(0);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindPhotoSessionById() {
        generateTestData();
        Optional<PhotoSession> photoSession = photoSessionRepository.findById(3);
        assertTrue(photoSession.isPresent());
        int expected = 3;
        int actual = photoSession.get().getId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllPhotoSessions() {
        generateTestData();
        int expected = 5;
        int actual = photoSessionRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeletePhotoSessionById() {
        generateTestData();
        photoSessionRepository.deleteById(3);
        int expected = 4;
        int actual = photoSessionRepository.findAll().size();
        assertEquals(expected, actual);
    }

    private void generateTestData() {
        for (int i = 0; i < 5; i++) {
            PhotoSession photoSession = PhotoSession.builder().duration(60).name(TEST).price(0).type(TEST).build();
            photoSessionRepository.save(photoSession);
        }
    }
}