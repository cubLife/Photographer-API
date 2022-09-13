package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.SocialNetwork;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(value = "test")
class SocialNetworkRepositoryTest {
    @Autowired
    private SocialNetworkRepository socialNetworkRepository;
    private static final String TEST = "test";

    @Test
    public void shouldAddNewSocialNetwork(){
        generateTeatData();
        SocialNetwork actual  = socialNetworkRepository.getById(1);
        SocialNetwork expected = new SocialNetwork(1,TEST,TEST,null);
        assertEquals(expected,actual);
    }

    @Test
    public void shouldFidSocialNetworkById(){
        generateTeatData();
        int actual=socialNetworkRepository.getById(3).getId();
        int expected =3;
        assertEquals(actual,expected);
    }

    @Test
    public void shouldFondAllSocialNetworks(){
        generateTeatData();
        int actual = socialNetworkRepository.findAll().size();
        int expected= 5;
        assertEquals(actual,expected);
    }

    @Test
    public void shouldDeleteSocialNetworkById(){
        generateTeatData();
        socialNetworkRepository.deleteById(1);
        int actual = socialNetworkRepository.findAll().size();
        int expected = 4;
        assertEquals(actual, expected);
    }

    private void generateTeatData(){
        for(int i = 0; i<5; i++){
            SocialNetwork socialNetwork = new SocialNetwork(TEST,TEST,null);
            socialNetworkRepository.save(socialNetwork);
        }
    }
}