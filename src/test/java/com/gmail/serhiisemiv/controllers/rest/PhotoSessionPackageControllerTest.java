package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoSessionPackageMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoSessionPackageModelAssembler;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import com.gmail.serhiisemiv.service.PhotoSessionPackageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {PhotoSessionPackageController.class})
class PhotoSessionPackageControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private PhotoSessionPackageService packageService;
    @MockBean
    private PhotoSessionPackageModelAssembler modelAssembler;
    @MockBean
    private PhotoSessionPackageMapper mapper;
    private static final String ROOT_URL = "/api/photo-session-packages";
    private static final PhotoSessionPackageDto SESSION_PACKAGE_DTO = PhotoSessionPackageDto.builder().name("premium")
            .duration(10).duration(30).numberPhotos(30).price(400).build();

    @Test
    void savePhotoSession() throws Exception {
        mockMvc.perform(post(ROOT_URL).content(objectMapper.writeValueAsString(SESSION_PACKAGE_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get(ROOT_URL + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(ROOT_URL + "/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void editPhotoSessionPackageById() throws Exception {
        mockMvc.perform(get(ROOT_URL + "/{id}", 1)
                        .content(objectMapper.writeValueAsString(SESSION_PACKAGE_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete(ROOT_URL+"/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}