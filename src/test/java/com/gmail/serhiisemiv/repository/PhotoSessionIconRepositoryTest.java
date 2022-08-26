package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.AvatarImage;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import com.gmail.serhiisemiv.modeles.PhotoSessionIcon;
import com.gmail.serhiisemiv.modeles.Photographer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.access.method.P;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PhotoSessionIconRepositoryTest {
    @Autowired
   private PhotoSessionIconRepository repository;
    @Autowired
    private PhotoSessionRepository photoSessionRepository;
    private static final byte[] PICTURE= new byte[5];
   private static final String TEST= "test";

    @Test
    void shouldSavePhotoSessionIcon() {
        createTestData();
        PhotoSessionIcon expected = new PhotoSessionIcon(1,PICTURE,new PhotoSession(1,TEST));
        PhotoSessionIcon actual = repository.getById(1);
        assertEquals(expected,actual);
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
        int expected = 6;
        int actual = repository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() {
        createTestData();
        repository.deleteById(1);
        int expected = 5;
        int actual = repository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void findByPhotoSession_Id() {
        createTestData();
        PhotoSessionIcon expected = new PhotoSessionIcon(1,PICTURE, new PhotoSession(TEST));
        PhotoSessionIcon actual = repository.findByPhotoSession_Id(1).get();
        assertEquals(expected, actual);
    }

    private void createTestData() {
        PhotoSession sessionOne = new PhotoSession(TEST);
        PhotoSession sessionTwo = new PhotoSession(TEST);
        photoSessionRepository.save(sessionOne);
        photoSessionRepository.save(sessionTwo);
        repository.save(new PhotoSessionIcon(PICTURE, sessionOne));
        for (int i = 0; i < 5; i++) {
            repository.save(new PhotoSessionIcon(PICTURE,sessionTwo));
        }
    }
}