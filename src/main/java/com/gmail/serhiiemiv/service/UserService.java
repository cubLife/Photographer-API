package com.gmail.serhiiemiv.service;

import com.gmail.serhiiemiv.exceptions.ServiceException;
import com.gmail.serhiiemiv.modeles.User;
import com.gmail.serhiiemiv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Input parameter can't be null");
        }
        try {
            userRepository.save(user);
        } catch (NumberFormatException e) {
            throw new ServiceException("Cant save costumer user " + user);
        }
    }

    public User findUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ServiceException("Can't find user with id - " + id);
        }
        return user.get();
    }

    public List<User> findAllUsers() {
        try {
            return userRepository.findAll();
        } catch (NullPointerException e) {
            throw new ServiceException("Can't find any users");
        }
    }

    public void deleteUserById(int id) {
        try {
            userRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ServiceException("Can't delete user with id = " + id);
        }
    }
}
