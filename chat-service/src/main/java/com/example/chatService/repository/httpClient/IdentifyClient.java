package com.example.chatService.repository.httpClient;

import com.example.chatService.config.WebSocketChannelInterceptor;
import com.example.chatService.dto.response.ApiResponse;
import com.example.chatService.dto.response.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "identify-service",url = "${app.identify.url}",configuration = {WebSocketChannelInterceptor.class})
public interface IdentifyClient {
    @GetMapping("/identify/users/{userId}")
    ApiResponse<UserProfileResponse> getInfo(@PathVariable String userId);

}
