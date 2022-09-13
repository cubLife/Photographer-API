package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.PhotographerDto;
import com.gmail.serhiisemiv.dto.mappers.PhotographerMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotographerDtoModelAssembler;
import com.gmail.serhiisemiv.service.PhotographerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {PhotographerController.class})
@ActiveProfiles(value = "test")
class PhotographerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private  PhotographerService photographerService;
    @MockBean
    private PhotographerDtoModelAssembler modelAssembler;
    @MockBean
    private  PhotographerMapper mapper;
    private static final String API_PHOTOGRAPHERS_URL = "/api/photographers";
    private static final PhotographerDto PHOTOGRAPHER = PhotographerDto.builder().firstName("Jon").lastName("Doe")
            .phone("jon_doe@gmail.com").phone("+48123456789").aboutMyself("Hello world").build();

    @Test
    void addPhotographer() throws Exception {
        mockMvc.perform(post(API_PHOTOGRAPHERS_URL).content(objectMapper.writeValueAsString(PHOTOGRAPHER))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get(API_PHOTOGRAPHERS_URL+"/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateAboutMySelf() throws Exception {
        mockMvc.perform(put(API_PHOTOGRAPHERS_URL+"/{id}/edit-about",1)
                .param("aboutMyself", "Hello world")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateEmail() throws Exception {
        mockMvc.perform(put(API_PHOTOGRAPHERS_URL+"/{id}/edit-email",1)
                        .param("email", "jon_doe@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updatePhone() throws Exception {
        mockMvc.perform(put(API_PHOTOGRAPHERS_URL+"/{id}/edit-phone",1)
                        .param("phone", "+481234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deletePhotographerById() throws Exception {
        mockMvc.perform(delete(API_PHOTOGRAPHERS_URL+"/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}