package com.example.chatService.mapper;

import com.example.chatService.dto.request.MessageRequest;
import com.example.chatService.dto.response.MessageResponse;
import com.example.chatService.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message toMessage(MessageRequest messageRequest);

    @Mapping(target = "conversationId", source = "conversation.id")
    MessageResponse toMessageResponse(Message message);
}
