package com.example.chatService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "conversations")
public class Conversation {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Size(max = 50)
    @NotNull
    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("'admin'")
    @Column(name = "admin_id", nullable = false, length = 50)
    private String adminId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = Instant.now();
    }

}