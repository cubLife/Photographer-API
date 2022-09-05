package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import com.gmail.serhiisemiv.repository.PhotoSessionPackageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PhotoSessionPackageServiceTest {
    @Mock
    private PhotoSessionPackageRepository mockPackageRepository;
    @Mock
    private PhotoSessionPackageService mockPackageService;
    @InjectMocks
    private PhotoSessionPackageService packageService;

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
    void shouldInvokeSavePhotoSessionPackage() {
        packageService.savePhotoSessionPackage(new PhotoSessionPackage());
        verify(mockPackageRepository, times(1)).save(new PhotoSessionPackage());
    }

    @Test
    void shouldInvokeFindPhotoSessionPackageById() {
        mockPackageService.findPhotoSessionPackageById(anyInt());
        verify(mockPackageService,times(1)).findPhotoSessionPackageById(anyInt());
    }

    @Test
    void shouldInvokeFindAllPhotoSessionPackages() {
        mockPackageService.findAllPhotoSessionPackages();
        verify(mockPackageService,times(1)).findAllPhotoSessionPackages();
    }

    @Test
    void shouldInvokeDeletePhotoSessionPackageById() {
        packageService.deletePhotoSessionPackageById(anyInt());
        verify(mockPackageRepository,times(1)).deleteById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> packageService.savePhotoSessionPackage(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockPackageService).savePhotoSessionPackage(new PhotoSessionPackage());
        assertThrows(ServiceException.class, () -> mockPackageService.savePhotoSessionPackage(new PhotoSessionPackage()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPackageService).findPhotoSessionPackageById(anyInt());
        assertThrows(ServiceException.class, () -> mockPackageService.findPhotoSessionPackageById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockPackageService).findAllPhotoSessionPackages();
        assertThrows(ServiceException.class, () -> mockPackageService.findAllPhotoSessionPackages());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockPackageService).deletePhotoSessionPackageById(anyInt());
        assertThrows(ServiceException.class, () -> mockPackageService.deletePhotoSessionPackageById(anyInt()));
    }
}