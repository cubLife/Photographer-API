package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.PhotoAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoAlbumRepository extends JpaRepository<PhotoAlbum, Integer> {
}
