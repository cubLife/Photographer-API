package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoSessionIconDto;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import com.gmail.serhiisemiv.modeles.PhotoSessionIcon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoSessionIconMapperTest {
    @Autowired
    private PhotoSessionIconMapper mapper;
    private static final PhotoSessionIcon PHOTO_SESSION_ICON = new PhotoSessionIcon(1,new byte[10]
            ,new PhotoSession(1,"Test"));

    @Test
    void shouldReturnPhotoSessionIcon() {
        PhotoSessionIconDto expected = PhotoSessionIconDto.builder().id(1).PhotoSessionId(1).build();
        PhotoSessionIconDto actual = mapper.toDto(PHOTO_SESSION_ICON);
        assertEquals(expected,actual);
    }
}