package com.varchar6.petcast.domain.review.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.review.query.dto.ReviewDTO;
import com.varchar6.petcast.domain.review.query.service.ReviewService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "queryReviewController")
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/company/{companyId}")
    private ResponseEntity<ResponseMessage> findEventByCompany(@PathVariable Integer companyId,
        @RequestParam Integer lastReviewId){

        List<ReviewDTO> responseReview = reviewService.findReviewByCompany(companyId, lastReviewId);

        return ResponseEntity.ok(new ResponseMessage(200, "이벤트 조회 성공"
            , responseReview));
    }

    @GetMapping("/user/{userId}")
    private ResponseEntity<ResponseMessage> findEventByCustomer(@PathVariable Integer userId,
        @RequestParam Integer lastReviewId){

        List<ReviewDTO> responseReview = reviewService.findEventByCustomer(userId, lastReviewId);

        return ResponseEntity.ok(new ResponseMessage(200, "이벤트 조회 성공"
            , responseReview));
    }


}
