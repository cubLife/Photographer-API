package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.PhotoSessionIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoSessionIconRepository extends JpaRepository<PhotoSessionIcon, Integer> {

    Optional<PhotoSessionIcon> findByPhotoSession_Id(int id);
}
