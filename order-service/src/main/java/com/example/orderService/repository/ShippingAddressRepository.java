package com.example.orderService.repository;

import com.example.orderService.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress,String> {
    @Query("SELECT a FROM ShippingAddress a" +
            " WHERE a.order.orderId = :orderId")
    Optional<ShippingAddress> findByOrderId(@Param("orderId") String orderId);
}
