package com.example.orderService.controller;

import com.example.orderService.dto.request.ShippingAddressRequest;
import com.example.orderService.dto.response.ApiResponse;
import com.example.orderService.service.ShippingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/shipping-address")
@RestController
@RequiredArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @PutMapping("/{OrderId}")
    ApiResponse<String> updateShippingAddress(@PathVariable String OrderId, @RequestBody ShippingAddressRequest request){
        return ApiResponse.<String>builder()
                .message(shippingAddressService.updateShippingAddress(OrderId,request))
                .build();
    }
}
