package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.PhotoSessionPackage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PhotoSessionPackageRepositoryTest {
    @Autowired
    private PhotoSessionPackageRepository repository;
    private static final int ID = 1;
    private static final String NAME = "test";
    private static final int NUMBER_PHOTOS = 30;
    private static final int PRICE = 100;
    private static final int DURATION= 60;

    @Test
    void shouldSavePhotoSessionPackage(){
        createTestData();
        PhotoSessionPackage expected = new PhotoSessionPackage(ID,NAME,NUMBER_PHOTOS,PRICE,DURATION);
        PhotoSessionPackage actual = repository.getById(1);
        assertEquals(expected,actual);
    }

    @Test
    void shouldFindById(){
        createTestData();
        PhotoSessionPackage expected = new PhotoSessionPackage(ID,NAME,NUMBER_PHOTOS,PRICE,DURATION);
        PhotoSessionPackage actual = repository.findById(1).get();
        assertEquals(expected,actual);
    }

    @Test
    void shouldFindAll(){
        createTestData();
        int expected = 5;
        int actual = repository.findAll().size();
        assertEquals(expected,actual);
    }

    @Test
    void shouldDeleteById(){
        createTestData();
        repository.deleteById(1);
        int expected = 4;
        int actual = repository.findAll().size();
        assertEquals(expected,actual);
    }

    private void createTestData(){
                for(int i=0;i<5;i++){
                    repository.save(new PhotoSessionPackage(NAME,NUMBER_PHOTOS,PRICE,DURATION));
                }
    }

}