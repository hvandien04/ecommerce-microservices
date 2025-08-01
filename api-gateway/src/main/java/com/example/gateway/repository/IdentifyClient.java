package com.example.gateway.repository;

import com.example.gateway.dto.request.IntrospectRequest;
import com.example.gateway.dto.response.ApiResponse;
import com.example.gateway.dto.response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentifyClient {
    @PostExchange(url = "/identify/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest introspectRequest);
}
