package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.mappers.PhotoSessionIconMapper;
import com.gmail.serhiisemiv.service.PhotoSessionIconService;
import com.gmail.serhiisemiv.service.PhotoSessionService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {PhotoSessionIconController.class})
@ActiveProfiles(value = "test")
class PhotoSessionIconControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private  PhotoSessionIconService iconService;
    @MockBean
    private  PhotoSessionService sessionService;
    @MockBean
    private  PhotoSessionIconMapper mapper;
    private static final String ROOT_URL = "/api/photo-session-icons";
    private static final MockMultipartFile FILE = new MockMultipartFile("file",
            "hello.jpeg",
            MediaType.IMAGE_JPEG_VALUE,
            new byte[10]);

    @Test
    void addAPhotoSessionIcon() throws Exception {
        mockMvc.perform(multipart(ROOT_URL).file(FILE).param("sessionId", "1")).
                andExpect(status().isCreated()).
                andDo(print());
    }

    @Test
    void getByPhotoSessionId() throws Exception {
    }

    @Test
    void getPictureByPhotoSessionId() {
    }

    @Test
    void changeIcon() {
    }
}