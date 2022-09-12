package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.mappers.AvatarImageMapper;
import com.gmail.serhiisemiv.modeles.AvatarImage;
import com.gmail.serhiisemiv.modeles.Photographer;
import com.gmail.serhiisemiv.service.AvatarImageService;
import com.gmail.serhiisemiv.service.PhotographerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {AvatarImageController.class})
class AvatarImageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AvatarImageService avatarImageService;
    @MockBean
    private PhotographerService photographerService;
    @MockBean
    private AvatarImageMapper mapper;

    private MockMultipartFile FILE = new MockMultipartFile("file",
            "hello.jpeg",
            MediaType.IMAGE_JPEG_VALUE,
            new byte[10]);

    private static final String API_AVATAR_IMAGES_URL = "/api/avatar-images";
    private static final String API_AVATAR_IMAGES_PHOTOGRAPHER_ID = "/api/avatar-images/photographer-id/{id}";
    private static final String API_AVATAR_IMAGES_PHOTOGRAPHER_ID_PICTURE = "/api/avatar-images/photographer-id/{id}/picture";

    @Test
    void addAvatarImage() throws Exception {
        mockMvc.perform(multipart(API_AVATAR_IMAGES_URL).file(FILE).param("photographerId", "1")).
                andExpect(status().isCreated()).
                andDo(print());
    }

    @Test
    void getAvatarImageByPhotographerId() throws Exception {

    }

    @Test
    void getAvatarImagePicture() throws Exception {
    }

    @Test
    void changeAvatar() {
    }
}