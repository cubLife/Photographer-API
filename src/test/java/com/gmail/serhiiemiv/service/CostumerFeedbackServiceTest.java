package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.CostumerFeedback;
import com.gmail.serhiiemiv.repository.CostumerFeedbackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CostumerFeedbackServiceTest {
    @Mock
    private CostumerFeedbackRepository costumerFeedbackRepository;
    @Mock
    private CostumerFeedbackService mockCostumerFeedbackService;
    @InjectMocks
    private CostumerFeedbackService costumerFeedbackService;
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
    void shouldInvokeSaveCostumer() {
        costumerFeedbackService.saveCostumerFeedback(new CostumerFeedback());
        verify(costumerFeedbackRepository, times(1)).save(new CostumerFeedback());
    }

    @Test
    void findCostumerFeedbackById() {
        mockCostumerFeedbackService.findCostumerFeedbackById(anyInt());
        verify(mockCostumerFeedbackService, times(1)).findCostumerFeedbackById(anyInt());
    }

    @Test
    void findAllCostumerFeedbacks() {
        mockCostumerFeedbackService.findAllCostumerFeedbacks();
        verify(mockCostumerFeedbackService, times(1)).findAllCostumerFeedbacks();
    }

    @Test
    void deleteCostumerFeedbackById() {
        mockCostumerFeedbackService.deleteCostumerFeedbackById(anyInt());
        verify(mockCostumerFeedbackService, times(1)).deleteCostumerFeedbackById(anyInt());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveMethodCall() {
        String expected = "Input parameter can't be null";
        String actual = assertThrows(IllegalArgumentException.class, () ->
                costumerFeedbackService.saveCostumerFeedback(null)
        ).getMessage();
        assertTrue(expected.contains(actual));
    }

    @Test
    void shouldThrowServiceExceptionWhenAddMethodCall() {
        doThrow(ServiceException.class).when(mockCostumerFeedbackService).saveCostumerFeedback(new CostumerFeedback());
        assertThrows(ServiceException.class, () ->
                mockCostumerFeedbackService.saveCostumerFeedback(new CostumerFeedback()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindByIdMethodCall() {
        doThrow(ServiceException.class).when(mockCostumerFeedbackService).findCostumerFeedbackById(anyInt());
        assertThrows(ServiceException.class, () ->
                mockCostumerFeedbackService.findCostumerFeedbackById(anyInt()));
    }

    @Test
    void shouldThrowServiceExceptionWhenFindAllMethodCAll() {
        doThrow(ServiceException.class).when(mockCostumerFeedbackService).findAllCostumerFeedbacks();
        assertThrows(ServiceException.class, () ->
                mockCostumerFeedbackService.findAllCostumerFeedbacks());
    }

    @Test
    void shouldThrowServiceExceptionWhenDeleteByIdMethodCall() {
        doThrow(ServiceException.class).when(mockCostumerFeedbackService).deleteCostumerFeedbackById(anyInt());
        assertThrows(ServiceException.class, () ->
                mockCostumerFeedbackService.deleteCostumerFeedbackById(anyInt()));
    }
}