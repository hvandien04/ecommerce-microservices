package com.example.reviewService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "product_id", length = 50)
    private String productId;

    @Size(max = 50)
    @Column(name = "user_id", length = 50)
    private String userId;

    @Lob
    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private Integer rating;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "reviewed_at")
    private Instant reviewedAt;

    @PrePersist
    private void prePersist() {
        this.reviewedAt = Instant.now();
    }

}