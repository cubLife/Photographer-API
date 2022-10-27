package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Integer> {
}
