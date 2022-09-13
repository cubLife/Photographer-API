package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.mappers.PhotoMapper;
import com.gmail.serhiisemiv.modelAsemblers.PhotoDtoModelAssembler;
import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import com.gmail.serhiisemiv.service.PhotoAlbumService;
import com.gmail.serhiisemiv.service.PhotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {PhotoController.class})
@ActiveProfiles(value = "test")
class PhotoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private  PhotoService photoService;
    @MockBean
    private  PhotoAlbumService photoAlbumService;
    @MockBean
    private PhotoDtoModelAssembler photoDtoModelAssembler;
    @MockBean
    private PhotoMapper mapper;
    private static final String ROOT_URL = "/api/photos";
    private static final MockMultipartFile FILE = new MockMultipartFile("file",
            "hello.jpeg",
            MediaType.IMAGE_JPEG_VALUE,
            new byte[10]);

    @Test
    void savePhoto() throws Exception {
        mockMvc.perform(multipart(ROOT_URL).file(FILE).param("photoAlbumId", "1")).
                andExpect(status().isCreated()).
                andDo(print());
    }

    @Test
    void saveListPhotos() throws Exception {
        MockMultipartFile file = new MockMultipartFile("files",
                "hello.jpeg",
                MediaType.IMAGE_JPEG_VALUE,
                new byte[10]);
        mockMvc.perform(multipart(ROOT_URL+"/list").file(file).file(file).param("photoAlbumId", "1")).
                andExpect(status().isCreated()).
                andDo(print());
    }

    @Test
    void getImageById() {

    }

    @Test
    void getAllImages() {
    }

    @Test
    void findPhotoById() {
    }

    @Test
    void findAllPhotos() {
    }

    @Test
    void findAllByPhotoAlbum() {
    }

    @Test
    void getFirstImageByAlbumId() {
    }

    @Test
    void replacePhoto() {
    }

    @Test
    void deletePhotoById() {
    }
}