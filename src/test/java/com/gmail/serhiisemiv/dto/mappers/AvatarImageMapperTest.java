package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.AvatarImageDto;
import com.gmail.serhiisemiv.modeles.AvatarImage;
import com.gmail.serhiisemiv.modeles.Photographer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AvatarImageMapperTest {
    @Autowired
    private AvatarImageMapper mapper;
    private static final AvatarImage AVATAR_IMAGE = new AvatarImage(1,new byte[10], Photographer.builder().id(5).build());

    @Test
    void shouldReturnAvatarImageDto() {
        AvatarImageDto expected = new AvatarImageDto(1,5);
        AvatarImageDto actual =mapper.toDto(AVATAR_IMAGE);
        assertEquals(expected,actual);

    }
}