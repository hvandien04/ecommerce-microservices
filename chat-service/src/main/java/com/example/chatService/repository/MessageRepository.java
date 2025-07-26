package com.example.chatService.repository;

import com.example.chatService.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,String> {
    List<Message> findAllByConversationIdOrderBySentAtDesc(String conversationId, Pageable pageable);

    @Query("""
    SELECT m FROM Message m
    WHERE m.sentAt = (
        SELECT MAX(m2.sentAt)
        FROM Message m2
        WHERE m2.conversation.id = m.conversation.id
    )
    ORDER BY m.sentAt DESC
""")
    List<Message> findLastMessagesPerConversation(Pageable pageable);
}
