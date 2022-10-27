package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
    public boolean existsCostumerByEmail(String email);
    public Optional<Costumer> findCostumerByEmail(String email);
}
