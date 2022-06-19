package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.AvatarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarImageRepository extends JpaRepository<AvatarImage, Integer> {
}
