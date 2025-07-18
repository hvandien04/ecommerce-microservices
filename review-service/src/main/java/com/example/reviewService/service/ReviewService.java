package com.example.reviewService.service;

import com.example.reviewService.dto.request.ReviewRequest;
import com.example.reviewService.dto.response.ReviewResponse;
import com.example.reviewService.entity.Review;
import com.example.reviewService.exception.AppException;
import com.example.reviewService.exception.ErrorCode;
import com.example.reviewService.mapper.ReviewMapper;
import com.example.reviewService.repository.ReviewRepository;
import com.example.reviewService.repository.httpClient.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final OrderClient orderClient;

    public ReviewResponse createReview(ReviewRequest reviewRequest){
        var userId = getUserId();
        Review review = reviewMapper.toReview(reviewRequest);
        var orderStatus = orderClient.getOrderStatus(review.getProductId()).getResult();
        if(!orderStatus)
            throw new AppException(ErrorCode.BUY_PRODUCT_BEFORE);
        review.setUserId(userId);
        return reviewMapper.toReviewResponse(reviewRepository.save(review));

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


}
