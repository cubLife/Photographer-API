package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.Photographer;
import com.gmail.serhiisemiv.modeles.SocialNetwork;
import com.gmail.serhiisemiv.repository.SocialNetworkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SocialNetworkService {
    private final SocialNetworkRepository socialNetworkRepository;
    private final Logger error = LoggerFactory.getLogger("com.gmail.serhiisemiv.error");
    private final Logger debug = LoggerFactory.getLogger("com.gmail.serhiisemiv.debug");

    @Autowired
    public SocialNetworkService(SocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    public void saveSocialNetwork(SocialNetwork socialNetwork) {
        if (socialNetwork == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            debug.debug("Start saving new social network{}", socialNetwork);
            socialNetworkRepository.save(socialNetwork);
            debug.debug("SocialNetwork is saved{}", socialNetwork);
        } catch (NumberFormatException e) {
            error.error("Cant save social network - " + socialNetwork, e);
            throw new ServiceException("Cant save social network");
        }
    }

    public SocialNetwork findSocialNetworkById(int id) {
        debug.debug("Start returned social network with id - {}", id);
        Optional<SocialNetwork> socialNetwork = socialNetworkRepository.findById(id);
        if (socialNetwork.isEmpty()) {
            error.error("social network is not present", new ServiceException("Can't find social network with id - " + id));
            throw new ServiceException("Can't find social network with id");
        }
        debug.debug("Social network was returned - {}", socialNetwork.get());
        return socialNetwork.get();
    }

    public List<SocialNetwork> findAllSocialNetworks() {
        debug.debug("Starting returning all social networks");
        try {
            List<SocialNetwork> photographers = socialNetworkRepository.findAll();
            debug.debug("All social networks was returned");
            return photographers;
        } catch (NullPointerException e) {
            error.error("Can't find any social networks - " + e.getMessage(), e);
            throw new ServiceException("Cant find any social networks");
        }
    }

    public void deleteSocialNetworkById(int id) {
        debug.debug("Starting delete social network with id - {}", id);
        try {
            socialNetworkRepository.deleteById(id);
            debug.debug("Social network was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove social network with id - " + id, e);
            throw new ServiceException("Can't delete social network with id");
        }
    }
}
