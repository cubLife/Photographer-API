package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.PhotoSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoSessionRepository extends JpaRepository<PhotoSession, Integer> {
}
