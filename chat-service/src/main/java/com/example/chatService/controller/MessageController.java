package com.example.chatService.controller;

import com.example.chatService.dto.response.ApiResponse;
import com.example.chatService.dto.response.MessageResponse;
import com.example.chatService.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping
public class MessageController {

    private final ChatService chatService;

    @GetMapping("/message/{conversationId}")
    ApiResponse<List<MessageResponse>> GetHistory(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "20") int size, @PathVariable String conversationId) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ApiResponse.<List<MessageResponse>>builder()
                .result(chatService.getMessageHistory(pageable,conversationId))
                .build();
    }

    @GetMapping("/message/list")
    ApiResponse<List<MessageResponse>> GetMyHistory(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ApiResponse.<List<MessageResponse>>builder()
                .result(chatService.getListMessage(pageable))
                .build();
    }
}
