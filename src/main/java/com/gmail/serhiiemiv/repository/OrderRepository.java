package com.gmail.serhiiemiv.repository;

import com.gmail.serhiiemiv.modeles.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
