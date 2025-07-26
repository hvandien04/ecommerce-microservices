package com.example.chatService.service;

import com.example.chatService.dto.request.MessageRequest;
import com.example.chatService.dto.request.UserIdsRequest;
import com.example.chatService.dto.response.ApiResponse;
import com.example.chatService.dto.response.MessageResponse;
import com.example.chatService.dto.response.UserProfileResponse;
import com.example.chatService.dto.response.UserResponse;
import com.example.chatService.entity.Message;
import com.example.chatService.mapper.MessageMapper;
import com.example.chatService.repository.MessageRepository;
import com.example.chatService.repository.httpClient.IdentifyBatchClient;
import com.example.chatService.repository.httpClient.IdentifyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;
    private final ConversationService conversationService;
    private final IdentifyClient identifyClient;
    private final IdentifyBatchClient identifyBatchClient;


    public void sendMessages(MessageRequest messageRequest, Principal principal){
        String userId = principal.getName();
        Message message = messageMapper.toMessage(messageRequest);
        message.setSenderId(userId);
        if(messageRequest.getToUserId() == null) {
            message.setConversation(conversationService.CreateConversation(userId));
            messageRepository.save(message);

            MessageResponse response = messageMapper.toMessageResponse(message);
            ApiResponse<UserProfileResponse> user = identifyClient.getInfo(userId);
            response.setUser(user.getResult());
            simpMessagingTemplate.convertAndSend("/topic/admin-inbox", response);
        } else {
            message.setConversation(conversationService.CreateConversation(messageRequest.getToUserId()));
            messageRepository.save(message);

            MessageResponse response = messageMapper.toMessageResponse(message);
            ApiResponse<UserProfileResponse> user = identifyClient.getInfo(userId);
            response.setUser(user.getResult());
            simpMessagingTemplate.convertAndSendToUser(messageRequest.getToUserId(), "/queue/messages", response);
        }

    }

    private String getUserId(){
        String userId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof Jwt jwt){
            userId = jwt.getClaimAsString("userId");
        }

        if(userId == null){
            throw new IllegalStateException("Cannot get user id");
        }
        return userId;
    }

    public List<MessageResponse> getMessageHistory(Pageable pageable, String ConversationId) {
        return messageRepository.findAllByConversationIdOrderBySentAtDesc(ConversationId,pageable).stream().map(messageMapper::toMessageResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<MessageResponse> getListMessage(Pageable pageable) {
        List<Message> messages = messageRepository.findLastMessagesPerConversation(pageable);

        UserIdsRequest userIdsRequest = UserIdsRequest.builder().userIds(messages.stream().map(m -> m.getConversation().getUserId()).toList()).build();
        ApiResponse<List<UserProfileResponse>> userResponses = identifyBatchClient.getBatchInfo(userIdsRequest);

        if (userResponses.getResult().isEmpty()) {
            throw new RuntimeException("Không lấy được thông tin người dùng");
        }

        Map<String, UserProfileResponse> userMap = userResponses.getResult().stream()
                .collect(Collectors.toMap(UserProfileResponse::getUserId, u -> u));
        return messages.stream()
                .map(m -> MessageResponse.builder()
                        .senderId(m.getSenderId())
                        .content(m.getContent())
                        .conversationId(m.getConversation().getId())
                        .sentAt(m.getSentAt())
                        .user(userMap.get(m.getConversation().getUserId()))
                        .build())
                .toList();
    }
}
