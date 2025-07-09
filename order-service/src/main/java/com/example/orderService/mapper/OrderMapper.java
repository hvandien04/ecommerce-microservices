package com.example.orderService.mapper;

import com.example.orderService.dto.request.OrderRequest;
import com.example.orderService.dto.response.OrderResponse;
import com.example.orderService.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, ShippingAddressMapper.class})
public interface OrderMapper {
    Order ToOrder(OrderRequest request);
    OrderResponse toOrderResponse(Order order);
}
