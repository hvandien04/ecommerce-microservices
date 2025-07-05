package com.example.cartService.service;

import com.example.cartService.Mapper.CartMapper;
import com.example.cartService.dto.request.CartRequest;
import com.example.cartService.dto.response.ProductResponse;
import com.example.cartService.entity.CartItem;
import com.example.cartService.exception.AppException;
import com.example.cartService.exception.ErrorCode;
import com.example.cartService.repository.CartRepository;
import com.example.cartService.repository.httpClient.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductClient productClient;

    public String Create(CartRequest request) {
        ProductResponse productResponse = productClient.getByProductId(request.getProductId()).getResult();
        if(productResponse.getStock() < request.getQuantity()){
            throw new AppException(ErrorCode.STOCK_NOT_ENOUGH);
        }
        var optionalItem  = cartRepository.findUserIdAndProductId(request.getProductId(),request.getUserId());
        if(optionalItem .isEmpty()){
            CartItem cartItem = cartMapper.toCartItem(request);
            cartRepository.save(cartItem);
            return "Added to cart successfully";
        }
        else{
            CartItem existing = optionalItem.get();
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            cartRepository.save(existing);
            return "Updated cart successfully";
        }
    }
}
