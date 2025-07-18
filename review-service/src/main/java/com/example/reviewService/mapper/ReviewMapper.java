package com.example.reviewService.mapper;

import com.example.reviewService.dto.request.ReviewRequest;
import com.example.reviewService.dto.response.ReviewResponse;
import com.example.reviewService.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewRequest reviewRequest);
    ReviewResponse toReviewResponse(Review review);
}
