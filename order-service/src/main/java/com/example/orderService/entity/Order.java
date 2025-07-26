package com.example.orderService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "orders")
public class Order {
    @Id
    @Size(max = 50)
    @Column(name = "order_id", nullable = false, length = 50)
    private String orderId;

    @Size(max = 50)
    @Column(name = "user_id", length = 50)
    private String userId;

    @Size(max = 30)
    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "total_amount", precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "shipping_fee", precision = 12, scale = 2)
    private BigDecimal shippingFee;

    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name ="order_id")
    private ShippingAddress shippingAddress;

    @PrePersist
    private void prePersist() {
        this.createdAt = Instant.now();
    }
}