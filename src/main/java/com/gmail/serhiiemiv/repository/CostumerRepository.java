package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
}
