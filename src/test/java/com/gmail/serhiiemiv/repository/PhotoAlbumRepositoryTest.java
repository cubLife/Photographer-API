package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.PhotoAlbum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PhotoAlbumRepositoryTest {
    @Autowired
    private PhotoAlbumRepository photoAlbumRepository;
    private static final String TEST = "test";

    @Test
    void shouldSavePhotoAlbum(){
        generateTestData();
        PhotoAlbum expected = PhotoAlbum.builder().id(1).name(TEST).build();
        PhotoAlbum actual = photoAlbumRepository.findAll().get(0);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindPhotoAlbumById(){
        generateTestData();
        Optional<PhotoAlbum> photoAlbum = photoAlbumRepository.findById(3);
        assertTrue(photoAlbum.isPresent());
        int expected = 3;
        int actual = photoAlbum.get().getId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllPhotoAlbums(){
        generateTestData();
        int expected = 5;
        int actual = photoAlbumRepository.findAll().size();
        assertEquals(expected,actual);
    }

    @Test
    void shouldDeletePhotoById(){
        generateTestData();
        photoAlbumRepository.deleteById(3);
        int expected  = 4;
        int actual = photoAlbumRepository.findAll().size();
        assertEquals(expected, actual);
    }

    private void generateTestData(){
        for (int i=0; i<5; i++){
            PhotoAlbum photoAlbum = PhotoAlbum.builder().name(TEST).build();
            photoAlbumRepository.save(photoAlbum);
        }
    }
}