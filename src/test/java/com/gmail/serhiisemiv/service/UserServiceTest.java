package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.User;
import com.gmail.serhiisemiv.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles(value = "test")
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService mockUserService;
    @InjectMocks
    private UserService userService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = openMocks(this);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldInvokeSaveUser() {
        userService.saveUser(new User());
        verify(userRepository, times(1)).save(new User());
    }

    @Test
    void shouldInvokeFindUserById() {
        mockUserService.findUserById(anyInt());
        verify(mockUserService, times(1)).findUserById(anyInt());
    }

    @Test
    void shouldInvokeFindAllUsers() {
        mockUserService.findAllUsers();
        verify(mockUserService, times(1)).findAllUsers();
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(anyInt());
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> userService.saveUser(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockUserService).saveUser(new User());
        assertThrows(ServiceException.class, () -> mockUserService.saveUser(new User()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockUserService).findUserById(anyInt());
        assertThrows(ServiceException.class, () -> mockUserService.findUserById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockUserService).findAllUsers();
        assertThrows(ServiceException.class, () -> mockUserService.findAllUsers());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockUserService).deleteUserById(anyInt());
        assertThrows(ServiceException.class, () -> mockUserService.deleteUserById(anyInt()));
    }
}