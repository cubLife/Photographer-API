package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.PhotoSessionDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoSessionMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoSessionDtoModelAssembler;
import com.gmail.serhiisemiv.service.PhotoSessionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
@ContextConfiguration(classes = {PhotoSessionController.class})
@ActiveProfiles(value = "test")
@WebAppConfiguration
class PhotoSessionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PhotoSessionService photoSessionService;
    @MockBean
    private PhotoSessionMapper mapper;
    @MockBean
    private PhotoSessionDtoModelAssembler modelAssembler;
    private static final String API_PHOTO_SESSIONS_URL = "/api/photo-sessions";
    private static final PhotoSessionDto PHOTO_SESSION = new PhotoSessionDto(1, "Test");


    @Test
    void savePhotoSession() throws Exception {
        mockMvc.perform(post(API_PHOTO_SESSIONS_URL).content(objectMapper.writeValueAsString(PHOTO_SESSION))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get(API_PHOTO_SESSIONS_URL+"/{id}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(API_PHOTO_SESSIONS_URL+"/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete(API_PHOTO_SESSIONS_URL+"/{id}",1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void editPhotoSession() throws Exception {
        mockMvc.perform(put(API_PHOTO_SESSIONS_URL+"/{id}",1)
                .content(objectMapper.writeValueAsString(PHOTO_SESSION))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}