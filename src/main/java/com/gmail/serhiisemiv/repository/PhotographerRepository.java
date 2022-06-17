package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Integer> {
}
