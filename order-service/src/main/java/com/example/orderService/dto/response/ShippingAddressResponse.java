package com.example.orderService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShippingAddressResponse {
    private Long id;
    private String fullName;
    private String phone;
    private String addressLine;
    private String city;
    private String district;
    private String province;

}
