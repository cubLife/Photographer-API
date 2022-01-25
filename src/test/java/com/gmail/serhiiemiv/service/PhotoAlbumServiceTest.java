package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.PhotoAlbum;
import com.gmail.serhiiemiv.repository.PhotoAlbumRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PhotoAlbumServiceTest {
    @Mock
    private PhotoAlbumRepository photoAlbumRepository;
    @Mock
    private PhotoAlbumService mockPhotoAlbumService;
    @InjectMocks
    private PhotoAlbumService photoAlbumService;
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
    void shouldInvokeSavePhotoAlbum() {
        photoAlbumService.savePhotoAlbum(new PhotoAlbum());
        verify(photoAlbumRepository, times(1)).save(new PhotoAlbum());
    }

    @Test
    void shouldInvokeFindPhotoAlbumById() {
        mockPhotoAlbumService.findPhotoAlbumById(anyInt());
        verify(mockPhotoAlbumService, times(1)).findPhotoAlbumById(anyInt());
    }

    @Test
    void shouldInvokeFindAllPhotoAlbum() {
        mockPhotoAlbumService.findAllPhotoAlbums();
        verify(mockPhotoAlbumService, times(1)).findAllPhotoAlbums();
    }

    @Test
    void deletePhotoAlbumById() {
        photoAlbumService.deletePhotoAlbumById(anyInt());
        verify(photoAlbumRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> photoAlbumService.savePhotoAlbum(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoAlbumService).savePhotoAlbum(new PhotoAlbum());
        assertThrows(ServiceException.class, () -> mockPhotoAlbumService.savePhotoAlbum(new PhotoAlbum()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoAlbumService).findPhotoAlbumById(anyInt());
        assertThrows(ServiceException.class, () -> mockPhotoAlbumService.findPhotoAlbumById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockPhotoAlbumService).findAllPhotoAlbums();
        assertThrows(ServiceException.class, () -> mockPhotoAlbumService.findAllPhotoAlbums());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPhotoAlbumService).deletePhotoAlbumById(anyInt());
        assertThrows(ServiceException.class, () -> mockPhotoAlbumService.deletePhotoAlbumById(anyInt()));
    }
}