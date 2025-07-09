package com.example.orderService.repository.httpClient;

import com.example.orderService.config.AuthenticationRequestInterceptor;
import com.example.orderService.dto.request.ProductsIdRequest;
import com.example.orderService.dto.response.ApiResponse;
import com.example.orderService.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service",url = "${app.service.product}",configuration = {AuthenticationRequestInterceptor.class})
public interface ProductClient {
    @PostMapping("/product/batch")
    ApiResponse<List<ProductResponse>> getByProductIds(@RequestBody ProductsIdRequest productsIdRequest);
}
