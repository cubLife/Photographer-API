package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.mappers.CostumerMapper;
import com.gmail.serhiisemiv.modelAsemblers.CostumerDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.Costumer;
import com.gmail.serhiisemiv.service.CostumerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
@ContextConfiguration(classes = {CostumerController.class})
@ActiveProfiles(value = "test")
class CostumerControllerTest {
    @MockBean
    private CostumerService costumerService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CostumerDtoModelAssembler modelAssembler;
    @MockBean
    private CostumerMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String API_COSTUMERS_URL = "/api/costumers";
    private static final Costumer COSTUMER = Costumer.builder().firstName("Jon").lastName("Doe").email("gon_doe@gmail.com").build();


    @Test
    void saveCostumer() throws Exception {
        mockMvc.perform(post(API_COSTUMERS_URL).content(objectMapper.writeValueAsString(COSTUMER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());

    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get(API_COSTUMERS_URL + "/{costumer-id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get(API_COSTUMERS_URL +"/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteCostumerById() throws Exception {
        mockMvc.perform(delete(API_COSTUMERS_URL + "/{id}", 1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}