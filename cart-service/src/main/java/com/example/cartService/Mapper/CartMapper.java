package com.example.cartService.Mapper;

import com.example.cartService.dto.request.CartRequest;
import com.example.cartService.dto.response.CartResponse;
import com.example.cartService.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartItem toCartItem(CartRequest request);
    CartResponse toCartResponse(CartItem cartItem);
}
