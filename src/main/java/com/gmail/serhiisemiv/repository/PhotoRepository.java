package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {
    List<Photo> findAllByPhotoAlbum_Id(int photoAlbumId);

    Photo findFirstByPhotoAlbum_Id(int photoAlbumId);
}
