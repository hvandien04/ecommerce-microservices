package com.example.paymentService.mapper;

import com.example.paymentService.dto.request.PaymentRequest;
import com.example.paymentService.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toPayment(PaymentRequest paymentRequest);
}
