package com.example.cartService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "user_id", length = 50,nullable = false)
    private String userId;

    @Column(name = "product_id" ,nullable = false)
    private String productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "added_at")
    private Instant addedAt;

    @PrePersist
    private void prePersist() {
        this.addedAt = Instant.now();
    }

}