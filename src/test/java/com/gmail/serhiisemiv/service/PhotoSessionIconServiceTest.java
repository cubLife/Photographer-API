package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.PhotoSessionIcon;
import com.gmail.serhiisemiv.repository.PhotoSessionIconRepository;
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
class PhotoSessionIconServiceTest {
    @Mock
    private PhotoSessionIconRepository mockIconRepository;
    @Mock
    private PhotoSessionIconService mockIconService;
    @InjectMocks
    private PhotoSessionIconService iconService;

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
    void shouldInvokeSaveMethod() {
        iconService.save(new PhotoSessionIcon());
        verify(mockIconRepository,times(1)).save(new PhotoSessionIcon());
    }

    @Test
    void shouldInvokeFindByPhotoSessionId() {
        mockIconService.findByPhotoSessionId(anyInt());
        verify(mockIconService,times(1)).findByPhotoSessionId(anyInt());
    }

    @Test
    void shouldInvokeFindById() {
        mockIconService.findById(anyInt());
        verify(mockIconService,times(1)).findById(anyInt());
    }

    @Test
    void shouldInvokeDeletePhotoSessionIconById() {
        mockIconService.deletePhotoSessionIconById(anyInt());
        verify(mockIconService,times(1)).deletePhotoSessionIconById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () ->
                iconService.save(null)
        ).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenSaveMethodCall(){
        doThrow(ServiceException.class).when(mockIconService).save(new PhotoSessionIcon());
        assertThrows(ServiceException.class, ()-> mockIconService.save(new PhotoSessionIcon()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockIconService).findById(anyInt());
        assertThrows(ServiceException.class, () ->
                mockIconService.findById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockIconService).deletePhotoSessionIconById(anyInt());
        assertThrows(ServiceException.class, () ->
                mockIconService.deletePhotoSessionIconById(anyInt()));
    }

}