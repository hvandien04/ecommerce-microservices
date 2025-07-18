package com.example.reviewService.repository.httpClient;

import com.example.reviewService.config.AuthenticationRequestInterceptor;
import com.example.reviewService.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service",configuration = {AuthenticationRequestInterceptor.class}, url = "${app.service.order}")
public interface OrderClient {
    @GetMapping("/order/status/{ProductId}")
    ApiResponse<Boolean> getOrderStatus(@PathVariable String ProductId);
}
