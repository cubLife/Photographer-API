package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
