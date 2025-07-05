package com.example.cartService.controller;

import com.example.cartService.dto.request.CartRequest;
import com.example.cartService.dto.response.ApiResponse;
import com.example.cartService.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ApiResponse<String> create(@RequestBody CartRequest request) {
        return ApiResponse.<String>builder()
                .message(cartService.Create(request))
                .build();
    }
}
