package com.example.cartService.controller;

import com.example.cartService.dto.request.CartRequest;
import com.example.cartService.dto.response.ApiResponse;
import com.example.cartService.dto.response.ProductResponse;
import com.example.cartService.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class CartController {
    private final CartService cartService;

    @PostMapping
    ApiResponse<String> create(@RequestBody CartRequest request) {
        return ApiResponse.<String>builder()
                .message(cartService.Create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProductResponse>> getAllByUserId() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(cartService.getMyCart())
                .build();
    }
}
