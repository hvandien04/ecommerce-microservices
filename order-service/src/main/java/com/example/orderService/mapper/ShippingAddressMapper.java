package com.example.orderService.mapper;

import com.example.orderService.dto.request.ShippingAddressRequest;
import com.example.orderService.dto.response.ShippingAddressResponse;
import com.example.orderService.entity.ShippingAddress;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShippingAddressMapper {
    ShippingAddress ToShippingAddress(ShippingAddressRequest request);
    ShippingAddressResponse toShippingAddressResponse(ShippingAddress shippingAddress);

    void UpdateShippingAddress(@MappingTarget ShippingAddress shippingAddress, ShippingAddressRequest request);
}
