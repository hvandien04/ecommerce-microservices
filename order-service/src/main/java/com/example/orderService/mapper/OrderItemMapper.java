package com.example.orderService.mapper;

import com.example.orderService.dto.request.OrderItemRequest;
import com.example.orderService.dto.response.OrderItemResponse;
import com.example.orderService.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem ToOrderItem(OrderItemRequest request);
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
