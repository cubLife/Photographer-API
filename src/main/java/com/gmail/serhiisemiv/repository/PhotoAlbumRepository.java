package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.PhotoAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoAlbumRepository extends JpaRepository<PhotoAlbum, Integer> {

    List<PhotoAlbum> findByPhotoSession_Id(int id);
}
