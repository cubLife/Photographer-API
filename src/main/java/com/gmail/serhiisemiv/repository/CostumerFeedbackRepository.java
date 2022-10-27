package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.CostumerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumerFeedbackRepository extends JpaRepository<CostumerFeedback, Integer> {

}
