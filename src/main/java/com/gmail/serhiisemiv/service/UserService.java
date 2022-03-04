package com.gmail.serhiisemiv.service;

import com.gmail.serhiisemiv.exceptions.ServiceException;
import com.gmail.serhiisemiv.modeles.User;
import com.gmail.serhiisemiv.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger error = LoggerFactory.getLogger(this.getClass());
    private final Logger debug = LoggerFactory.getLogger(this.getClass());
    private final Logger info = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        if (user == null) {
            error.error("Input parameter was null", new IllegalArgumentException("Input parameter can't be null"));
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            info.info("Start saving new user {}", user);
            userRepository.save(user);
            debug.debug("User is saved{}", user);
        } catch (NumberFormatException e) {
            error.error("Cant save user  - " + user, e);
            throw new ServiceException("Cant save user");
        }
    }

    public User findUserById(int id) {
        info.info("Start returned user with id - {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            error.error("User is not present", new ServiceException("Can't find user with id - " + id));
            throw new ServiceException("Can't find user with id");
        }
        debug.debug("User was returned - {}", user.get());
        return user.get();
    }

    public List<User> findAllUsers() {
        info.info("Starting returning all users");
        try {
            List<User> users = userRepository.findAll();
            debug.debug("All users was returned");
            return users;
        } catch (NullPointerException e) {
            error.error("Can't find any users - " + e.getMessage(), e);
            throw new ServiceException("Can't find any users");
        }
    }

    public void deleteUserById(int id) {
        info.info("Starting delete user with id - {}", id);
        try {
            userRepository.deleteById(id);
            debug.debug("User was deleted id - {}", id);
        } catch (NoSuchElementException e) {
            error.error("Can't remove user with id - " + id, e);
            throw new ServiceException("Can't delete user with id");
        }
    }
}
