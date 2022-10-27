package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.AvatarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarImageRepository extends JpaRepository<AvatarImage, Integer> {
    public Optional<AvatarImage> findByPhotographer_Id(int photographerId);
}
