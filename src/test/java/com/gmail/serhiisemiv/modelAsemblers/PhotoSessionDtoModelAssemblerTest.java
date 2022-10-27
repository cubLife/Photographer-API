package com.gmail.serhiisemiv.modelAsemblers;

import com.gmail.serhiisemiv.dto.PhotoSessionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoSessionDtoModelAssemblerTest {
    @Autowired
    private PhotoSessionDtoModelAssembler modelAssembler;

    @Test
    void toModel() {
        PhotoSessionDto expected = new PhotoSessionDto(1, "Test");
        PhotoSessionDto actual = modelAssembler.toModel(expected).getContent();
        String expectedLinks = "<http://localhost/api/photo-sessions/1>;rel=\"self\",<http://localhost/api/photo-sessions/list>" +
                ";rel=\"photoSessions\",<http://localhost/api/photo-session-icons/session-id/1/icon>" +
                ";rel=\"icon\",<http://localhost/api/photo-albums/session-id/1/list>;rel=\"photoAlbums\"";
        String actualLinks = modelAssembler.toModel(expected).getLinks().toString();
        assertEquals(expected, actual);
        assertEquals(expectedLinks, actualLinks);

    }
}