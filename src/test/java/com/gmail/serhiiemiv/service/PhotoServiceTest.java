package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.Photo;
import com.gmail.serhiiemiv.repository.PhotoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PhotoServiceTest {
    @Mock
    private PhotoRepository photoRepository;
    @Mock
    private PhotoService mockPhotoService;
    @InjectMocks
    private PhotoService photoService;
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
    void shouldInvokeSavePhoto() {
        photoService.savePhoto(new Photo());
        verify(photoRepository, times(1)).save(new Photo());
    }

    @Test
    void shouldInvokeFindPhotoById() {
        mockPhotoService.findPhotoById(anyInt());
        verify(mockPhotoService, times(1)).findPhotoById(anyInt());
    }

    @Test
    void shouldInvokeFindAllPhotos() {
        mockPhotoService.findAllPhotos();
        verify(mockPhotoService, times(1)).findAllPhotos();
    }

    @Test
    void shouldInvokeDeletePhotoById() {
        photoService.deletePhotoById(anyInt());
        verify(photoRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> photoService.savePhoto(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoService).savePhoto(new Photo());
        assertThrows(ServiceException.class, () -> mockPhotoService.savePhoto(new Photo()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoService).findPhotoById(anyInt());
        assertThrows(ServiceException.class, () -> mockPhotoService.findPhotoById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockPhotoService).findAllPhotos();
        assertThrows(ServiceException.class, () -> mockPhotoService.findAllPhotos());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoService).deletePhotoById(anyInt());
        assertThrows(ServiceException.class, () -> mockPhotoService.deletePhotoById(anyInt()));
    }
}