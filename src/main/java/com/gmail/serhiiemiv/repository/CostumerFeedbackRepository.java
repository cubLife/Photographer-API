package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.CostumerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerFeedbackRepository extends JpaRepository<CostumerFeedback, Integer> {
}
