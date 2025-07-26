package com.example.chatService.controller;

import com.example.chatService.dto.request.MessageRequest;
import com.example.chatService.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void handleChatMessage(@Payload MessageRequest message,Principal principal){
        chatService.sendMessages(message,principal);
    }

}
