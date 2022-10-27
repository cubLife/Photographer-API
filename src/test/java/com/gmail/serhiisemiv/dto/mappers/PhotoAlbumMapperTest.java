package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoAlbumDto;
import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoAlbumMapperTest {
@Autowired
PhotoAlbumMapper mapper;
private static final PhotoAlbum PHOTO_ALBUM = new PhotoAlbum(1,"Test",new PhotoSession(1, "Test"));
    @Test
    void shouldReturnPhotoAlbumDto() {
        PhotoAlbumDto expected= new PhotoAlbumDto(1,"Test",1 );
        PhotoAlbumDto actual = mapper.toDto(PHOTO_ALBUM);
        assertEquals(expected,actual);
    }

    @Test
    void listToDto() {
        PhotoAlbumDto photoAlbumDto =  new PhotoAlbumDto(1,"Test",1 );
        List<PhotoAlbumDto> expected = List.of(photoAlbumDto,photoAlbumDto,photoAlbumDto);
        List<PhotoAlbumDto> actual =mapper.listToDto(List.of(PHOTO_ALBUM, PHOTO_ALBUM,PHOTO_ALBUM));
        for (int i =0;i<expected.size();i++){
            assertEquals(expected.get(i),actual.get(i));
        }
    }
}