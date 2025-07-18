package com.example.orderService.service;

import com.example.orderService.dto.request.OrderItemRequest;
import com.example.orderService.dto.request.OrderRequest;
import com.example.orderService.dto.request.ProductsIdRequest;
import com.example.orderService.dto.response.*;
import com.example.orderService.entity.Order;
import com.example.orderService.entity.OrderItem;
import com.example.orderService.entity.ShippingAddress;
import com.example.orderService.exception.AppException;
import com.example.orderService.exception.ErrorCode;
import com.example.orderService.mapper.OrderMapper;
import com.example.orderService.mapper.ShippingAddressMapper;
import com.example.orderService.repository.OrderItemRepository;
import com.example.orderService.repository.OrderRepository;
import com.example.orderService.repository.ShippingAddressRepository;
import com.example.orderService.repository.httpClient.ProductClient;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final ShippingAddressMapper shippingAddressMapper;
    private final IdGeneratorService idGeneratorService;
    private final ShippingAddressRepository shippingAddressRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public String createOrder(OrderRequest orderRequest){
        String userId = getUserId();
        Order order = orderMapper.ToOrder(orderRequest);
        order.setUserId(userId);
        order.setStatus("CREATED");
        order.setOrderId(idGeneratorService.generateRandomId("ORD", orderRepository::existsById));
        ShippingAddress shippingAddress = shippingAddressMapper.ToShippingAddress(orderRequest.getShippingAddressRequest());
        List<String> productIds = orderRequest.getOrderItemsRequest().stream().map(OrderItemRequest::getProductId).toList();
        ProductsIdRequest productsIdRequest = ProductsIdRequest.builder().productsId(productIds).build();
        ApiResponse<List<ProductResponse>> product = productClient.getByProductIds(productsIdRequest);
        if(product.getResult() == null)
            throw new AppException(ErrorCode.DEFECTIVE_PRODUCT);
        Map<String, ProductResponse> productMap = product.getResult().stream().collect(Collectors.toMap(ProductResponse::getProductsId, Function.identity()));
        List<OrderItem> orderItem = orderRequest.getOrderItemsRequest().stream().map(
                orderItemRequest -> {
                    ProductResponse productClient = productMap.get(orderItemRequest.getProductId());
                    return OrderItem.builder()
                            .order(order)
                            .productId(productClient.getProductsId())
                            .quantity(orderItemRequest.getQuantity())
                            .unitPrice(productClient.getPrice())
                            .build();
                }
        ).toList();
        order.setTotalAmount(
                orderItem.stream()
                        .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItem);
        shippingAddress.setOrder(order);
        shippingAddressRepository.save(shippingAddress);
        return "Create Order Success";
    }

    private static String getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = null;
        if(authentication.getPrincipal() instanceof Jwt jwt){
            userId = jwt.getClaimAsString("userId");
        }
        if(userId == null){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return userId;
    }

    public OrderResponse getOrderById(String orderId){
        String userId = getUserId();
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        if(!userId.equals(order.getUserId())){
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        return orderMapper.toOrderResponse(order);
    }

    public List<OrderResponse> getMyAllOrders(){
        String userId = getUserId();
        return orderRepository.findAllByUserId(userId).stream().map(orderMapper::toOrderResponse).toList();
    }

    public void updateOrderStatus(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setStatus("PAID");
        orderRepository.save(order);
    }

    public Boolean getOrderStatusByProductId(String productId) {
        String userId = getUserId();
        Order order = orderRepository.checkOrderStatus(productId, userId).orElseThrow(()-> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return order.getStatus().equals("SUCCESS");

    }
}
