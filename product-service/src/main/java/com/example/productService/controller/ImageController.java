package com.example.productService.controller;

import com.example.productService.dto.request.ProductImageRequest;
import com.example.productService.dto.response.ApiResponse;
import com.example.productService.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/image")
@RequiredArgsConstructor
@RestController
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/{productId}")
    ApiResponse<String> createImage(@PathVariable String productId, @RequestBody ProductImageRequest imageUrl){
        return ApiResponse.<String>builder()
                .message(imageService.createImage(productId,imageUrl))
                .build();
    }

    @DeleteMapping("/{imageId}")
    ApiResponse<String> deleteImage(@PathVariable String imageId){
        return ApiResponse.<String>builder()
                .message(imageService.deleteImage(imageId))
                .build();
    }
}
