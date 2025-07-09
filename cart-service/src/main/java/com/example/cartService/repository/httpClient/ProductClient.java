package com.example.cartService.repository.httpClient;

import com.example.cartService.config.AuthenticationRequestInterceptor;
import com.example.cartService.dto.request.ProductsIdRequest;
import com.example.cartService.dto.response.ApiResponse;
import com.example.cartService.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service",url = "${app.service.product}",configuration = {AuthenticationRequestInterceptor.class})
public interface ProductClient {
    @GetMapping("/product/{productId}")
    ApiResponse<ProductResponse> getByProductId(@PathVariable String productId);

    @PostMapping("/product/batch")
    ApiResponse<List<ProductResponse>> getByProductIds(@RequestBody ProductsIdRequest productIds);
}
