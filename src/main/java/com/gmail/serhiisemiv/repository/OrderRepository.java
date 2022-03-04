package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByCostumer_Id(int costumerId);
}
