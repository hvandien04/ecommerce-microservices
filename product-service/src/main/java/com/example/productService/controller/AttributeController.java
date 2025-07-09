package com.example.productService.controller;

import com.example.productService.dto.request.ProductAttributeRequest;
import com.example.productService.dto.response.ApiResponse;
import com.example.productService.service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/attribute")
@RestController
@RequiredArgsConstructor
public class AttributeController {
    private final AttributeService attributeService;

    @PostMapping("/{ProductId}")
    ApiResponse<String> createAttribute(@PathVariable String ProductId,@RequestBody ProductAttributeRequest request){
        return ApiResponse.<String>builder()
                .message(attributeService.CreateAttribute(ProductId,request))
                .build();
    }

    @DeleteMapping("/{attributeId}")
    ApiResponse<String> deleteAttribute(@PathVariable String attributeId){
        return ApiResponse.<String>builder()
                .message(attributeService.DeletedAttribute(attributeId))
                .build();
    }
}
