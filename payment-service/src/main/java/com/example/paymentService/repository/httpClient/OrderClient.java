package com.example.paymentService.repository.httpClient;

import com.example.paymentService.config.AuthenticationRequestInterceptor;
import com.example.paymentService.dto.response.ApiResponse;
import com.example.paymentService.dto.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service",url = "${app.service.order}", configuration = {AuthenticationRequestInterceptor.class})
public interface OrderClient {
    @GetMapping("/order/{orderId}")
    ApiResponse<OrderResponse> getOrderById(@PathVariable String orderId);
}
