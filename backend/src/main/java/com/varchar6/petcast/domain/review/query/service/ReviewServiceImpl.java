package com.varchar6.petcast.domain.review.query.service;

import com.varchar6.petcast.domain.review.query.dto.ReviewDTO;
import com.varchar6.petcast.domain.review.query.mapper.ReviewMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "queryReviewService")
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDTO> findReviewByCompany(Integer companyId, Integer lastReviewId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("companyId", String.valueOf(companyId));
        params.put("lastReviewId", String.valueOf(lastReviewId));

        List<ReviewDTO> reviewDTOS = reviewMapper.selectReviewByCompanyId(params);
        return reviewDTOS;
    }

    @Override
    public List<ReviewDTO> findEventByCustomer(Integer userId, Integer lastReviewId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customerId", String.valueOf(userId));
        params.put("lastReviewId", String.valueOf(lastReviewId));

        List<ReviewDTO> reviewDTOS = reviewMapper.selectReviewByCustomerId(params);

        return reviewDTOS;
    }
}
