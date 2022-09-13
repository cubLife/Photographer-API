package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.SocialNetwork;
import com.gmail.serhiisemiv.repository.SocialNetworkRepository;
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
class SocialNetworkServiceTest {
    @Mock
    private SocialNetworkRepository socialNetworkRepository;
    @Mock
    private SocialNetworkService mockSocialNetworkService;
    @InjectMocks
    private SocialNetworkService socialNetworkService;
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
    void shouldInvokeSaveSocialNetwork() {
        socialNetworkService.saveSocialNetwork(new SocialNetwork());
        verify(socialNetworkRepository, times(1)).save(new SocialNetwork());
    }

    @Test
    void shouldInvokeFindSocialNetworkById() {
        mockSocialNetworkService.findSocialNetworkById(anyInt());
        verify(mockSocialNetworkService,times(1)).findSocialNetworkById(anyInt());
    }

    @Test
    void shouldInvokeFindAllSocialNetworks() {
        mockSocialNetworkService.findAllSocialNetworks();
        verify(mockSocialNetworkService, times(1)).findAllSocialNetworks();
    }

    @Test
    void shouldInvokeDeleteSocialNetworkById() {
        socialNetworkService.deleteSocialNetworkById(anyInt());
        verify(socialNetworkRepository,times(1)).deleteById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> socialNetworkService.saveSocialNetwork(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockSocialNetworkService).saveSocialNetwork(new SocialNetwork());
        assertThrows(ServiceException.class, () -> mockSocialNetworkService.saveSocialNetwork(new SocialNetwork()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockSocialNetworkService).findSocialNetworkById(anyInt());
        assertThrows(ServiceException.class, () -> mockSocialNetworkService.findSocialNetworkById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockSocialNetworkService).findAllSocialNetworks();
        assertThrows(ServiceException.class, () -> mockSocialNetworkService.findAllSocialNetworks());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockSocialNetworkService).deleteSocialNetworkById(anyInt());
        assertThrows(ServiceException.class, () -> mockSocialNetworkService.deleteSocialNetworkById(anyInt()));
    }
}