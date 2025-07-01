package com.example.identifyService.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserCreateRequest {
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String gender;
}
