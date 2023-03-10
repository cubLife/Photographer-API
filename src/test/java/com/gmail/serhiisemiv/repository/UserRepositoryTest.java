//package com.gmail.serhiisemiv.repository;
//
//import com.gmail.serhiisemiv.modeles.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)


//
//@ActiveProfiles(value = "test")
//class UserRepositoryTest {
//    @Autowired
//    private UserRepository userRepository;
//    private static final String TEST = "test";
//
//    @Test
//    void shouldSaveUser() {
//        generateTestData();
//        User expected = new User(TEST, TEST,TEST,TEST,TEST,"+380977100205");
//        User actual = userRepository.findAll().get(0);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldFindUserById() {
//        generateTestData();
//        Optional<User> user = userRepository.findById(3);
//        assertTrue(user.isPresent());
//        int expected = 3;
//        int actual = user.get().getId();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldFindAllUsers() {
//        generateTestData();
//        int expected = 5;
//        int actual = userRepository.findAll().size();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldDeleteUserById() {
//        generateTestData();
//        userRepository.deleteById(3);
//        int expected = 4;
//        int actual = userRepository.findAll().size();
//        assertEquals(expected, actual);
//    }
//
//    private void generateTestData() {
//        for (int i = 0; i < 5; i++) {
//            User user = new User(TEST, TEST,TEST,TEST,TEST,"+380977100205");
//            userRepository.save(user);
//        }
//    }
//}