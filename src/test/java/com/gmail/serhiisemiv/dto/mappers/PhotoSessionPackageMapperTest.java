package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoSessionPackageMapperTest {
    @Autowired
    PhotoSessionPackageMapper mapper;
    private static final PhotoSessionPackage SESSION_PACKAGE = PhotoSessionPackage.builder().id(1)
            .name("Premium").duration(60).numberPhotos(30).price(500).build();

    @Test
    void shouldReturnPhotoSessionPackageDto() {
        PhotoSessionPackageDto expected = PhotoSessionPackageDto.builder().id(1)
                .name("Premium").duration(60).numberPhotos(30).price(500).build();
        PhotoSessionPackageDto actual = mapper.toDto(SESSION_PACKAGE);
    }
}