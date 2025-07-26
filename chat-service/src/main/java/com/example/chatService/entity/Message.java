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
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Size(max = 50)
    @NotNull
    @Column(name = "sender_id", nullable = false, length = 50)
    private String senderId;

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @ColumnDefault("0")
    @Column(name = "is_read")
    private Boolean isRead;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "sent_at")
    private Instant sentAt;

    @PrePersist
    private void prePersist() {
        this.sentAt = Instant.now();
    }

}