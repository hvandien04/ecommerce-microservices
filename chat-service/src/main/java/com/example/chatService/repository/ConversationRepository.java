package com.example.chatService.repository;

import com.example.chatService.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation,String> {
    Optional<Conversation> findByUserId(String userId);
}
