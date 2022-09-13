package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.mappers.CostumerFeedbackMapper;
import com.gmail.serhiisemiv.modelAsemblers.CostumerFeedbackDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import com.gmail.serhiisemiv.service.CostumerFeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {CostumerFeedbackController.class})
@ActiveProfiles(value = "test")
class CostumerFeedbackControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private  CostumerFeedbackService feedbackService;
    @MockBean
    private  CostumerFeedbackMapper mapper;
    @MockBean
    private  CostumerFeedbackDtoModelAssembler modelAssembler;
    private static final String API_FEEDBACKS_URL = "/api/feedbacks";
    private static final CostumerFeedback COSTUMER_FEEDBACK = CostumerFeedback.builder().firstName("Jon").lastName("Doe")
            .email("gon_doe@gmail.com").grade(5).feedback("hello world").build();

    @Test
    void saveCostumerFeedback() throws Exception {
        mockMvc.perform(post(API_FEEDBACKS_URL).content(objectMapper.writeValueAsString(COSTUMER_FEEDBACK))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get(API_FEEDBACKS_URL+"/{id}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(API_FEEDBACKS_URL+"/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete(API_FEEDBACKS_URL+"/{id}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}