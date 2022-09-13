package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.AvatarImage;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import com.gmail.serhiisemiv.modeles.Photographer;
import com.gmail.serhiisemiv.repository.AvatarImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles(value = "test")
class AvatarImageServiceTest {
    @Mock
    private AvatarImageService mockImageService;
    @Mock
    private AvatarImageRepository mockImageRepository;
    @InjectMocks
    private AvatarImageService avatarImageService;
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
    void shouldInvokeSaveAvatarImage() {
        avatarImageService.saveAvatarImage(new AvatarImage());
        verify(mockImageRepository, times(1)).save(new AvatarImage());
    }

    @Test
    void findByPhotographerId() {
        when(mockImageService.findByPhotographerId(anyInt())).thenReturn(new AvatarImage());
        AvatarImage avatarImage = mockImageService.findByPhotographerId(anyInt());
        assertNotNull(avatarImage);
        verify(mockImageService, times(1)).findByPhotographerId(anyInt());
    }

    @Test
    void findAvatarImageById() {
        when(mockImageService.findAvatarImageById(anyInt())).thenReturn(new AvatarImage());
        AvatarImage avatarImage = mockImageService.findAvatarImageById(anyInt());
        assertNotNull(avatarImage);
        verify(mockImageService, times(1)).findAvatarImageById(anyInt());
    }

    @Test
    void deleteAvatarImageById() {
        mockImageService.deleteAvatarImageById(anyInt());
        verify(mockImageService, times(1)).deleteAvatarImageById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () ->
                avatarImageService.saveAvatarImage(null)
        ).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenSaveMethodCall(){
        doThrow(ServiceException.class).when(mockImageService).saveAvatarImage(new AvatarImage());
        assertThrows(ServiceException.class, ()-> mockImageService.saveAvatarImage(new AvatarImage()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockImageService).findAvatarImageById(anyInt());
        assertThrows(ServiceException.class, () ->
                mockImageService.findAvatarImageById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByPhotographerIdMethodCall() {
        doThrow(ServiceException.class).when(mockImageService).findByPhotographerId(anyInt());
        assertThrows(ServiceException.class, () ->
                mockImageService.findByPhotographerId(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockImageService).deleteAvatarImageById(anyInt());
        assertThrows(ServiceException.class, () ->
                mockImageService.deleteAvatarImageById(anyInt()));
    }
}