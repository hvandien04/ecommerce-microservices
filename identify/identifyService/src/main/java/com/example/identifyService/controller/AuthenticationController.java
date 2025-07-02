package com.example.identifyService.controller;

import com.example.identifyService.dto.request.AuthenticationRequest;
import com.example.identifyService.dto.request.IntrospectRequest;
import com.example.identifyService.dto.response.ApiResponse;
import com.example.identifyService.dto.response.AuthenticationResponse;
import com.example.identifyService.dto.response.IntrospectResponse;
import com.example.identifyService.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        ResponseCookie cookie = ResponseCookie.from("token", authenticationResponse.getToken())
                .secure(false)
                .httpOnly(true)
                .path("/")
                .maxAge(30*24*60*60)
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationResponse)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        authenticationService.Logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }
}
