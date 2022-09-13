package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Photo;
import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(value = "test")
class PhotoRepositoryTest {
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PhotoAlbumRepository photoAlbumRepository;
    private static final String TEST = "test";

    @Test
    void shouldSavePhoto() {
        photoRepository.save(Photo.builder().name(TEST).size(1L).picture(new byte[0]).build());
        Photo expected = Photo.builder().id(1).name(TEST).size(1L).picture(new byte[0]).build();
        Photo actual = photoRepository.getById(1);
        assertEquals(expected, actual);

    }

    @Test
    void shouldSaveAllPhotos() {
        photoRepository.saveAll(List.of(Photo.builder().name(TEST).size(1L).picture(new byte[1]).build()
                ,Photo.builder().name(TEST).size(5L).picture(new byte[2]).build()));
        Photo photoOne = Photo.builder().id(1).name(TEST).size(1L).picture(new byte[0]).build();
        Photo photoTwo = Photo.builder().id(2).name(TEST).size(5L).picture(new byte[2]).build();
        List<Photo> expected = List.of(photoOne, photoTwo);
        List<Photo> actual = photoRepository.findAll();
        assertEquals(expected, actual);
    }


    @Test
    void shouldFindPhotoById() {
        generateTestDAta();
        Photo expected = Photo.builder().id(3).name(TEST).size(1L).picture(new byte[3]).build();
        Photo actual = photoRepository.getById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllPhotos() {
        generateTestDAta();
        int expected = 6;
        int actual = photoRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllPhotosByPhotoAlbumId() {
        generateTestDAta();
        int expected = 1;
        int actual = photoRepository.findAllByPhotoAlbum_Id(1).size();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeletePhotoById() {
        generateTestDAta();
        photoRepository.deleteById(1);
        int expected = 5;
        int actual = photoRepository.findAll().size();
        assertEquals(expected, actual);
    }

    @Test
    void findFirstByPhotoAlbum_Id() {
        generateTestDAta();
        int expected = 1;
        int actual = photoRepository.findFirstByPhotoAlbum_Id(1).getId();
        assertEquals(expected, actual);
    }

    private void generateTestDAta() {
        photoAlbumRepository.save(new PhotoAlbum());
        photoRepository.save(Photo.builder().name(TEST).size(1L).picture(new byte[3])
                .photoAlbum(photoAlbumRepository.findById(1).get()).build());
        for (int i = 0; i < 5; i++) {
            photoRepository.save(Photo.builder().name(TEST).size(1L).picture(new byte[3]).build());
        }
    }

}