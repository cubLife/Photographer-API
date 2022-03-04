package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.PhotoSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoSessionRepository extends JpaRepository<PhotoSession, Integer> {
}
