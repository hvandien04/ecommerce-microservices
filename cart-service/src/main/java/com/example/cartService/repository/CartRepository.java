package com.example.cartService.repository;

import com.example.cartService.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem,String> {

    @Query("SELECT a FROM CartItem a " +
            "WHERE a.userId = :userId " +
            "AND a.productId = :productId")
    Optional<CartItem> findUserIdAndProductId(@Param("productId") String productId,
                                              @Param("userId") String userId);

    List<CartItem> findAllByUserId(String userId);
}
