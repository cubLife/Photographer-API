package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Photographer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(value = "test")
class PhotographerRepositoryTest {
    @Autowired
    private PhotographerRepository photographerRepository;
    @Autowired
    private SocialNetworkRepository socialNetworkRepository;
    private static final String TEST = "test";
    private static final String PHONE = "+1234567890";
    private static final String EMAIL = "test@gmail.com";

    @Test
    public void shouldSavePhotographer() {
        generateTestData();
        Photographer actual = photographerRepository.getById(1);
        Photographer expected = Photographer.builder().id(1).login(TEST).password(TEST).firstName(TEST).lastName(TEST).email(TEST).aboutMyself(TEST).build();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetPhotographerById() {
        generateTestData();
        int actual = photographerRepository.getById(1).getId();
        int expected = 1;
        assertEquals(expected, actual);

    }

    @Test
    public void shouldGetAllPhotographers() {
        generateTestData();
        int actual = photographerRepository.findAll().size();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldDeletePhotographerById() {
        generateTestData();
        photographerRepository.deleteById(1);
        int actual = photographerRepository.findAll().size();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateAboutMySelf() {
        generateTestData();
        photographerRepository.updateAboutMySelf("Hi", 1);
        String actual = photographerRepository.findById(1).get().getAboutMyself();
        String expected = "Hi";
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateEmail() {
        generateTestData();
        photographerRepository.updateEmail("sample@gmail.com", 1);
        String actual = photographerRepository.findById(1).get().getEmail();
        String expected = "sample@gmail.com";
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdatePhone() {
        generateTestData();
        photographerRepository.updatePhone("000000000000", 1);
        String actual = photographerRepository.findById(1).get().getPhone();
        String expected = "000000000000";
        assertEquals(expected, actual);
    }

    private void generateTestData() {
        for (int i = 0; i < 5; i++) {
            Photographer photographer = Photographer.builder().login(TEST).password(TEST).firstName(TEST).lastName(TEST).email(TEST).phone(PHONE).aboutMyself(TEST).build();
            photographerRepository.save(photographer);
        }
    }
}