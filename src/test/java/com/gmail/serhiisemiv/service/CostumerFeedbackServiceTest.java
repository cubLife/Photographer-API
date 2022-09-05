package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.dto.CostumerFeedbackDto;
import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import com.gmail.serhiisemiv.repository.CostumerFeedbackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
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
    private static final String FIRST_NAME = "Jon";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "jon_doe@gmail.com";
    private static final String FEEDBACK = "Test";
    private static final int GRADE = 5;
    private static final long CREATION_DATE = 1234455566L;

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

    @Test
    void shouldCreateNewCostumerFeedback() {
        CostumerFeedbackDto feedbackDto = CostumerFeedbackDto.builder().firstName(FIRST_NAME).lastName(LAST_NAME).email(EMAIL)
                .feedback(FEEDBACK).grade(GRADE).build();
        CostumerFeedback expected = new CostumerFeedback(FIRST_NAME, LAST_NAME, new Date().getTime(), FEEDBACK, GRADE, EMAIL);
        CostumerFeedback actual = costumerFeedbackService.createNewCostumerFeedback(feedbackDto);
        assertEquals(expected, actual);
    }
}