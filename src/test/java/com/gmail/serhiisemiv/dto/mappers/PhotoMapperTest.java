package com.gmail.serhiisemiv.dto.mappers;

import com.gmail.serhiisemiv.dto.PhotoDto;
import com.gmail.serhiisemiv.modeles.Photo;
import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import com.gmail.serhiisemiv.modeles.PhotoSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PhotoMapperTest {
    @Autowired
    private PhotoMapper mapper;
    private static final Photo PHOTO = new Photo(1,new byte[10],"test.gpeg",1000L,
            new PhotoAlbum(1,"Test",new PhotoSession()));

    @Test
    void shouldReturnPhotoDto() {
        PhotoDto expected  = new PhotoDto(1,"test.gpeg",1000L,1);
        PhotoDto actual = mapper.toDto(PHOTO);
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnListPhotoDto() {
        PhotoDto photoDto  = new PhotoDto(1,"test.gpeg",1000L,1);
        List<PhotoDto> expected = List.of(photoDto,photoDto,photoDto);
        List<PhotoDto> actual = mapper.listToDto(List.of(PHOTO,PHOTO,PHOTO));
        for(int i=0;i<expected.size();i++){
            assertEquals(expected.get(i),actual.get(i));
        }
    }
}