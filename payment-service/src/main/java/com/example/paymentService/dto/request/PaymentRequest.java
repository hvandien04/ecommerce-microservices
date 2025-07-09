package com.example.paymentService.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {
    private String orderId;
    private String method;
    private BigDecimal totalAmount;
}
