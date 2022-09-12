package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.PhotoAlbumDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoAlbumDtoModelAssemblerTest {
    @Autowired
    private PhotoAlbumDtoModelAssembler modelAssembler;

    @Test
    void toModel() {
        PhotoAlbumDto expected = new PhotoAlbumDto(1, "test", 5);
        PhotoAlbumDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/photo-albums/1>;rel=\"self\",<http://localhost/api/photo-albums/list>" +
                ";rel=\"photoAlbums\",<http://localhost/api/photos/photo-album/1>;rel=\"photos\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);
    }
}