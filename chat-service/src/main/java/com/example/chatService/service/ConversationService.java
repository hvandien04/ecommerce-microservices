package com.example.chatService.service;

import com.example.chatService.entity.Conversation;
import com.example.chatService.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final IdGeneratorService idGeneratorService;
    private final ConversationRepository conversationRepository;

    public Conversation CreateConversation(String userId) {
        Optional<Conversation> optionalConversation = conversationRepository.findByUserId(userId);
        if (optionalConversation.isPresent()) {
            return optionalConversation.get();
        } else {
            Conversation conversation = Conversation.builder()
                    .id(idGeneratorService.generateRandomId("conv-", conversationRepository::existsById))
                    .userId(userId)
                    .adminId("admin")
                    .build();
            return conversationRepository.save(conversation);
        }
    }
}
