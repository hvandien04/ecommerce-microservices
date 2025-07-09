package com.example.paymentService.service;

import com.example.paymentService.dto.request.PaymentRequest;
import com.example.paymentService.dto.response.OrderResponse;
import com.example.paymentService.entity.Payment;
import com.example.paymentService.exception.AppException;
import com.example.paymentService.exception.ErrorCode;
import com.example.paymentService.mapper.PaymentMapper;
import com.example.paymentService.repository.PaymentRepository;
import com.example.paymentService.repository.httpClient.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderClient  orderClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public String createPayment(PaymentRequest paymentRequest){
        OrderResponse orderResponse = orderClient.getOrderById(paymentRequest.getOrderId()).getResult();
        if(orderResponse == null)
            throw new AppException(ErrorCode.DEFECTIVE_ORDER);
        Payment payment = paymentMapper.toPayment(paymentRequest);
        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);
        kafkaTemplate.send("payment-topic", orderResponse.getOrderId());
        return "Payment created successfully";
    }

}
