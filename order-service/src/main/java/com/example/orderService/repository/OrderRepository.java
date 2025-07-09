package com.example.orderService.repository;

import com.example.orderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    Collection<Order> findAllByUserId(String userId);
}
