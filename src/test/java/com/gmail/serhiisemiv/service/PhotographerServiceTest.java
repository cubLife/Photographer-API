package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Photographer;
import com.gmail.serhiisemiv.modeles.SocialNetwork;
import com.gmail.serhiisemiv.repository.PhotographerRepository;
import com.gmail.serhiisemiv.repository.SocialNetworkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles(value = "test")
class PhotographerServiceTest {
    @Mock
    private PhotographerRepository photographerRepository;
    @Mock
    private PhotographerService mockPhotographerService;
    @InjectMocks
    private PhotographerService photographerService;
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
    void shouldInvokeSavePhotographer() {
        photographerService.savePhotographer(new Photographer());
        verify(photographerRepository, times(1)).save(new Photographer());
    }

    @Test
    void shouldInvokeFindPhotographerById() {
        mockPhotographerService.findPhotographerById(anyInt());
        verify(mockPhotographerService, times(1)).findPhotographerById(anyInt());
    }

    @Test
    void shouldVerifyFindAllPhotographers() {
        mockPhotographerService.findAllPhotographers();
        verify(mockPhotographerService, times(1)).findAllPhotographers();
    }

    @Test
    void shouldInvokeDeletePhotographerById() {
        photographerService.deletePhotographerById(anyInt());
        verify(photographerRepository,times(1)).deleteById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> photographerService.savePhotographer(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockPhotographerService).savePhotographer(new Photographer());
        assertThrows(ServiceException.class, () -> mockPhotographerService.savePhotographer(new Photographer()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPhotographerService).findPhotographerById(anyInt());
        assertThrows(ServiceException.class, () -> mockPhotographerService.findPhotographerById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockPhotographerService).findAllPhotographers();
        assertThrows(ServiceException.class, () -> mockPhotographerService.findAllPhotographers());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPhotographerService).deletePhotographerById(anyInt());
        assertThrows(ServiceException.class, () -> mockPhotographerService.deletePhotographerById(anyInt()));
    }
}