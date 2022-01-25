package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.PhotoSession;
import com.gmail.serhiiemiv.repository.PhotoSessionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PhotoSessionServiceTest {
    @Mock
    private PhotoSessionRepository photoSessionRepository;
    @Mock
    private PhotoSessionService mockPhotoSessionService;
    @InjectMocks
    private PhotoSessionService photoSessionService;
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
    void shouldInvokeSavePhotoSession() {
        photoSessionService.savePhotoSession(new PhotoSession());
        verify(photoSessionRepository, times(1)).save(new PhotoSession());
    }

    @Test
    void shouldInvokeFindPhotoSessionById() {
        mockPhotoSessionService.findPhotoSessionById(anyInt());
        verify(mockPhotoSessionService, times(1)).findPhotoSessionById(anyInt());
    }

    @Test
    void shouldInvokeFindAllPhotoSessions() {
        mockPhotoSessionService.findAllPhotoSessions();
        verify(mockPhotoSessionService, times(1)).findAllPhotoSessions();
    }

    @Test
    void shouldInvokeDeletePhotoSessionById() {
        photoSessionService.deletePhotoSessionById(anyInt());
        verify(photoSessionRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> photoSessionService.savePhotoSession(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoSessionService).savePhotoSession(new PhotoSession());
        assertThrows(ServiceException.class, () -> mockPhotoSessionService.savePhotoSession(new PhotoSession()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoSessionService).findPhotoSessionById(anyInt());
        assertThrows(ServiceException.class, () -> mockPhotoSessionService.findPhotoSessionById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockPhotoSessionService).findAllPhotoSessions();
        assertThrows(ServiceException.class, () -> mockPhotoSessionService.findAllPhotoSessions());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoSessionService).deletePhotoSessionById(anyInt());
        assertThrows(ServiceException.class, () -> mockPhotoSessionService.deletePhotoSessionById(anyInt()));
    }
}