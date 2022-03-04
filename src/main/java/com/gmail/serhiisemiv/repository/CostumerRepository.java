package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
}
