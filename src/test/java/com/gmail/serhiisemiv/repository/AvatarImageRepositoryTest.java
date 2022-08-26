package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.AvatarImage;
import com.gmail.serhiisemiv.modeles.Photographer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AvatarImageRepositoryTest {
    @Autowired
    private AvatarImageRepository repository;
    @Autowired
    private PhotographerRepository photographerRepository;
    private static final byte[] PICTURE = new byte[5];
    private static final String TEST = "test";

    @Test
    void shouldSaveAvatarImage() {
        createTestData();
        AvatarImage expected = new AvatarImage(1, PICTURE, Photographer.builder().id(1).firstName(TEST).lastName(TEST).email(TEST)
                .phone(TEST).aboutMyself(TEST).build());
        AvatarImage actual = repository.getById(1);
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
    void findByPhotographer_Id() {
        createTestData();
        AvatarImage expected = new AvatarImage(1,new byte[10], Photographer.builder().id(2).firstName(TEST).lastName(TEST).email(TEST)
                .phone(TEST).aboutMyself(TEST).build());
        AvatarImage actual = repository.findByPhotographer_Id(2).get();
        assertEquals(expected, actual);
    }

    private void createTestData() {
        Photographer photographerOne = Photographer.builder().firstName(TEST).lastName(TEST).email(TEST)
                .phone(TEST).aboutMyself(TEST).build();
        photographerRepository.save(photographerOne);
        Photographer photographerTwo = Photographer.builder().firstName(TEST).lastName(TEST).email(TEST)
                .phone(TEST).aboutMyself(TEST).build();
        photographerRepository.save(photographerTwo);
        repository.save(new AvatarImage(new byte[10], photographerTwo));
        for (int i = 0; i < 5; i++) {
            repository.save(new AvatarImage(PICTURE, photographerOne));
        }
    }
}