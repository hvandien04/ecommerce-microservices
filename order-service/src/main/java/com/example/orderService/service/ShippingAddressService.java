package com.example.orderService.service;

import com.example.orderService.dto.request.ShippingAddressRequest;
import com.example.orderService.entity.ShippingAddress;
import com.example.orderService.exception.AppException;
import com.example.orderService.exception.ErrorCode;
import com.example.orderService.mapper.ShippingAddressMapper;
import com.example.orderService.repository.ShippingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;
    private final ShippingAddressMapper shippingAddressMapper;

    public String updateShippingAddress(String orderId, ShippingAddressRequest request){
        ShippingAddress shippingAddress = shippingAddressRepository.findByOrderId(orderId).orElseThrow(()-> new AppException(ErrorCode.SHIPPING_ADDRESS_NOT_FOUND));
        shippingAddressMapper.UpdateShippingAddress(shippingAddress, request);
        shippingAddressRepository.save(shippingAddress);
        return "Update Shipping Address Success";
    }
}
