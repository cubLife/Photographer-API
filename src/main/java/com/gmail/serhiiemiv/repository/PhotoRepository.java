package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {
}
