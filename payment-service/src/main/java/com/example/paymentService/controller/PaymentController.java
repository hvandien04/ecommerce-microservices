package com.example.paymentService.controller;

import com.example.paymentService.dto.request.PaymentRequest;
import com.example.paymentService.dto.response.ApiResponse;
import com.example.paymentService.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ApiResponse<String> createPayment(@RequestBody PaymentRequest paymentRequest){
        return ApiResponse.<String>builder()
                .result(paymentService.createPayment(paymentRequest))
                .build();
    }
}
