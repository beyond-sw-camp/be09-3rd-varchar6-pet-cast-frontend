package com.varchar6.petcast.domain.review.command.application.service;

import com.varchar6.petcast.domain.review.command.application.dto.request.ReviewCreateRequestDTO;
import com.varchar6.petcast.domain.review.command.application.dto.request.ReviewUpdateRequestDTO;
import com.varchar6.petcast.domain.review.command.application.dto.response.ReviewResponseDTO;
import com.varchar6.petcast.domain.review.command.domain.aggregate.Review;
import com.varchar6.petcast.domain.review.command.domain.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "commandReviewService")
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void insertReview(ReviewCreateRequestDTO reviewCreateRequestDTO) {
        Review review = modelMapper.map(reviewCreateRequestDTO, Review.class);

        reviewRepository.save(review);
    }

    @Override
    @Transactional
    public ReviewResponseDTO updateReview(ReviewUpdateRequestDTO reviewUpdateRequestDTO) {
        Review review = reviewRepository.findById(reviewUpdateRequestDTO.getId()).orElse(null);

        if(!"".equals(reviewUpdateRequestDTO.getComment()))
            review.setComment(reviewUpdateRequestDTO.getComment());
        else if (!"".equals(reviewUpdateRequestDTO.getTitle()))
            review.setTitle(reviewUpdateRequestDTO.getTitle());
        else if (reviewUpdateRequestDTO.getScore() != null)
            review.setScore(reviewUpdateRequestDTO.getScore());

        ReviewResponseDTO responseDTO = modelMapper.map(review, ReviewResponseDTO.class);

        return responseDTO;
    }

    @Override
    @Transactional
    public void deleteReview(int id) {

        reviewRepository.deleteById(id);
    }
}
