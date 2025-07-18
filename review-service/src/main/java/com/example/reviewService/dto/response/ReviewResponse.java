package com.example.reviewService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewResponse {
    private Long id;
    private String productId;
    private String userId;
    private String comment;
    private Integer rating;
    private Instant reviewedAt;
}
