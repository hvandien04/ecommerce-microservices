package com.example.identifyService.controller;

import com.example.identifyService.dto.request.UserCreateRequest;
import com.example.identifyService.dto.request.UserUpdatePasswordRequest;
import com.example.identifyService.dto.request.UserUpdateRequest;
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

    @GetMapping
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.CheckMyInfo())
                .build();
    }

    @PutMapping
    ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.UpdateUser(request))
                .build();
    }

    @PutMapping("/update-password")
    ApiResponse<String> updatePassword(@RequestBody UserUpdatePasswordRequest request){
        return ApiResponse.<String>builder()
                .message(userService.UpdatePassword(request))
                .build();
    }

}
