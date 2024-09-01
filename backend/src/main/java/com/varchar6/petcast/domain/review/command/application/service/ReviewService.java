package com.varchar6.petcast.domain.review.command.application.service;

import com.varchar6.petcast.domain.review.command.application.dto.request.ReviewCreateRequestDTO;
import com.varchar6.petcast.domain.review.command.application.dto.request.ReviewUpdateRequestDTO;
import com.varchar6.petcast.domain.review.command.application.dto.response.ReviewResponseDTO;

public interface ReviewService {

    int insertReview(ReviewCreateRequestDTO reviewCreateRequestDTO);

    ReviewResponseDTO updateReview(ReviewUpdateRequestDTO reviewUpdateRequestDTO);

    int deleteReview(int id);
}
