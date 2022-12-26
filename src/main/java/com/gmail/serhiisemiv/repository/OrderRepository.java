package com.gmail.serhiisemiv.repository;

import com.gmail.serhiisemiv.modeles.OrderStatus;
import com.gmail.serhiisemiv.modeles.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCostumer_Id(int costumerId);

    @Query("select o from Order o where o.orderStatus = :orderStatus")
    List<Order> findByOrderStatus(@Param(value = "orderStatus") OrderStatus orderStatus);
}
