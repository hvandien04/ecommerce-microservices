package com.example.paymentService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "order_id", length = 50)
    private String orderId;

    @Size(max = 100)
    @Column(name = "invoice_number", length = 100)
    private String invoiceNumber;

    @Column(name = "issued_at")
    private Instant issuedAt;

    @PrePersist
    void prePersist() {
        this.issuedAt = Instant.now();
    }

}