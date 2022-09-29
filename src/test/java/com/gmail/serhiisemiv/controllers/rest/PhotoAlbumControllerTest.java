package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.PhotoAlbumDto;
import com.gmail.serhiisemiv.dto.mappers.PhotoAlbumMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoAlbumDtoModelAssembler;
import com.gmail.serhiisemiv.service.PhotoAlbumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
@ContextConfiguration(classes = {PhotoAlbumController.class})
@ActiveProfiles(value = "test")
class PhotoAlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  PhotoAlbumService photoAlbumService;
    @MockBean
    private  PhotoAlbumDtoModelAssembler modelAssembler;
    @MockBean
    private  PhotoAlbumMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String API_PHOTO_ALBUMS_URL= "/api/photo-albums";
    private  static final PhotoAlbumDto PHOTO_ALBUM = new PhotoAlbumDto(1, "My photoAlbum", 1);

    @Test
    void savePhotoAlbum() throws Exception {
        mockMvc.perform(post(API_PHOTO_ALBUMS_URL).content(objectMapper.writeValueAsString(PHOTO_ALBUM))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get(API_PHOTO_ALBUMS_URL+"/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get(API_PHOTO_ALBUMS_URL+"/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findByPhotoSessionId() throws Exception {
        mockMvc.perform(get(API_PHOTO_ALBUMS_URL+"/session-id/{id}/list",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete(API_PHOTO_ALBUMS_URL+"/{id}",1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}