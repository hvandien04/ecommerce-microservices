package com.example.reviewService.controller;

import com.example.reviewService.dto.request.ReviewRequest;
import com.example.reviewService.dto.response.ApiResponse;
import com.example.reviewService.dto.response.ReviewResponse;
import com.example.reviewService.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    ApiResponse<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest){
        return ApiResponse.<ReviewResponse>builder()
                .result(reviewService.createReview(reviewRequest))
                .build();
    }
}
