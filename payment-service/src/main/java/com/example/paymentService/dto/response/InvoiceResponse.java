package com.example.paymentService.dto.response;

import jakarta.persistence.Column;

import java.time.Instant;

public class InvoiceResponse {
    private String orderId;
    private String invoiceNumber;
    private Instant issuedAt;
}
