package com.example.identifyService.controller;

import com.example.identifyService.dto.request.UserCreateRequest;
import com.example.identifyService.dto.response.ApiResponse;
import com.example.identifyService.dto.response.UserResponse;
import com.example.identifyService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.CreateUser(userCreateRequest))
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUserById(@PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(userId))
                .build();
    }
}
