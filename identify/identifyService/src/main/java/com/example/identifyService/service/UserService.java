package com.example.identifyService.service;

import com.example.identifyService.dto.request.UserCreateRequest;
import com.example.identifyService.dto.response.UserResponse;
import com.example.identifyService.entity.User;
import com.example.identifyService.exception.AppException;
import com.example.identifyService.exception.ErrorCode;
import com.example.identifyService.mapper.UserMapper;
import com.example.identifyService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final IdGeneratorService idGeneratorService;
    private final PasswordEncoder passwordEncoder;

    public UserResponse CreateUser(UserCreateRequest userCreateRequest) {
        System.out.println(userCreateRequest.getUsername());
        User user = userMapper.toUser(userCreateRequest);
        user.setUserId(idGeneratorService.generateRandomId("US", userRepository::existsById));
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        System.out.println(user.getUsername());
        System.out.println(Instant.now());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getUserById(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

}
