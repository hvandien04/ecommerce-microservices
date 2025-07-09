package com.example.cartService.service;

import com.example.cartService.Mapper.CartMapper;
import com.example.cartService.dto.request.CartRequest;
import com.example.cartService.dto.request.ProductsIdRequest;
import com.example.cartService.dto.response.ApiResponse;
import com.example.cartService.dto.response.ProductResponse;
import com.example.cartService.entity.CartItem;
import com.example.cartService.exception.AppException;
import com.example.cartService.exception.ErrorCode;
import com.example.cartService.repository.CartRepository;
import com.example.cartService.repository.httpClient.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductClient productClient;

    public String Create(CartRequest request) {
        ProductResponse productResponse = productClient.getByProductId(request.getProductId()).getResult();
        if(productResponse == null){
            throw new AppException(ErrorCode.DEFECTIVE_PRODUCT);
        }
        if(productResponse.getStock() < request.getQuantity()){
            throw new AppException(ErrorCode.STOCK_NOT_ENOUGH);
        }
        String userId = getUserId();
        var optionalItem  = cartRepository.findUserIdAndProductId(request.getProductId(),userId);
        if(optionalItem .isEmpty()){
            CartItem cartItem = cartMapper.toCartItem(request);
            cartItem.setUserId(userId);
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

    private static String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = null;

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            userId = jwt.getClaimAsString("userId");
        }

        if (userId == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return userId;
    }

    public List<ProductResponse> getMyCart() {
        String userId = getUserId();
        List<CartItem> cartItems = cartRepository.findAllByUserId(userId);
        List<String> productIds = cartItems.stream().map(CartItem::getProductId).collect(Collectors.toList());
        ProductsIdRequest productsIdRequest = ProductsIdRequest.builder().productsId(productIds).build();
        ApiResponse<List<ProductResponse>> result = productClient.getByProductIds(productsIdRequest);
        if (result.getResult() == null)
            throw new AppException(ErrorCode.DEFECTIVE_PRODUCT);
        return result.getResult();
    }
}
