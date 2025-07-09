package com.example.orderService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String orderId;
    private String userId;
    private String status;
    private BigDecimal totalAmount;
    private Instant createdAt;
    private List<OrderItemResponse> orderItems;
    private ShippingAddressResponse shippingAddress;
}
