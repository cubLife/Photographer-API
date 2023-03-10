package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.repository.CostumerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles(value = "test")
class CostumerServiceTest {
    @Mock
    private CostumerRepository costumerRepository;
    @Mock
    private CostumerService mockCostumerService;
    @InjectMocks
    private CostumerService costumerService;
    private AutoCloseable autoCloseable;
    private static final String EMAIL = "jon_doe@gmail.com";

    @BeforeEach
    void setUp() {
        autoCloseable = openMocks(this);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldInvokeSaveCostumer() {
        costumerService.saveCostumer(new Costumer());
        verify(costumerRepository, times(1)).save(new Costumer());
    }

    @Test
    void shouldInvokeFindCostumerById() {
        mockCostumerService.findCostumerById(anyInt());
        verify(mockCostumerService, times(1)).findCostumerById(anyInt());
    }

    @Test
    void findAllCostumers() {
        mockCostumerService.findAllCostumers();
        verify(mockCostumerService, times(1)).findAllCostumers();
    }

    @Test
    void deleteCostumerById() {
        costumerService.deleteCostumerById(anyInt());
        verify(costumerRepository, times(1)).deleteById(anyInt());
    }


    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () -> costumerService.saveCostumer(null)).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockCostumerService).saveCostumer(new Costumer());
        assertThrows(ServiceException.class, () -> mockCostumerService.saveCostumer(new Costumer()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockCostumerService).findCostumerById(anyInt());
        assertThrows(ServiceException.class, () -> mockCostumerService.findCostumerById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockCostumerService).findAllCostumers();
        assertThrows(ServiceException.class, () -> mockCostumerService.findAllCostumers());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockCostumerService).deleteCostumerById(anyInt());
        assertThrows(ServiceException.class, () -> mockCostumerService.deleteCostumerById(anyInt()));
    }

    @Test
    void existsCostumerByEmail() {
        when(mockCostumerService.existsCostumerByEmail(EMAIL)).thenReturn(true);
        boolean expected = true;
        boolean actual = mockCostumerService.existsCostumerByEmail(EMAIL);
        assertEquals(expected,actual);
    }
}