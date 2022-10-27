package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.PhotoSessionPackageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoSessionPackageDtoModelAssemblerTest {
    @Autowired
    private PhotoSessionPackageDtoModelAssembler modelAssembler;

    @Test
    void toModel() {
        PhotoSessionPackageDto expected = new PhotoSessionPackageDto(1, "Premium", 60, 500, 60);
        PhotoSessionPackageDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/photo-session-packages/1>" +
                ";rel=\"self\",<http://localhost/api/photo-session-packages/list>;rel=\"photoSessionPackages\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);
    }
}