package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoSessionDto;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoSessionMapperTest {
    @Autowired
    PhotoSessionMapper mapper;
    private static final PhotoSession PHOTO_SESSION = new PhotoSession(1,"Test");

    @Test
    void shouldReturnPhotoSessionDto() {
        PhotoSessionDto expected = new PhotoSessionDto(1,"Test");
        PhotoSessionDto actual = mapper.toDto(PHOTO_SESSION);
        assertEquals(expected,actual);
    }
}