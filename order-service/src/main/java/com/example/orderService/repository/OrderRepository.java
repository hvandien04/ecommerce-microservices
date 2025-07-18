package com.example.orderService.repository;

import com.example.orderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    Collection<Order> findAllByUserId(String userId);

    @Query("SELECT a FROM Order a " +
    "JOIN a.orderItems i " + "WHERE i.productId = :productId AND a.userId =:userId ")
    Optional<Order> checkOrderStatus(@Param("productId") String productId, @Param("userId") String userId);
}
