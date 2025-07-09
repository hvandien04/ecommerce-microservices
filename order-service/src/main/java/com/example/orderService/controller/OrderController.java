package com.example.orderService.controller;

import com.example.orderService.dto.request.OrderRequest;
import com.example.orderService.dto.response.ApiResponse;
import com.example.orderService.dto.response.OrderResponse;
import com.example.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    ApiResponse<String> createOrder(@RequestBody OrderRequest orderRequest){
        return ApiResponse.<String>builder()
                .message(orderService.createOrder(orderRequest))
                .build();
    }

    @GetMapping("/{orderId}")
    ApiResponse<OrderResponse> getOrderById(@PathVariable String orderId){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrderById(orderId))
                .build();
    }
    @GetMapping("/my-orders")
    ApiResponse<List<OrderResponse>> getMyAllOrders(){
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getMyAllOrders())
                .build();
    }

    @KafkaListener(topics = "payment-topic")
    public void listenPaymentTopic(String OrderId){
        orderService.updateOrderStatus(OrderId);
    }

}
