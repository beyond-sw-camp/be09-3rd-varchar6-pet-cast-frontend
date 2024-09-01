package com.varchar6.petcast.domain.review.command.domain.repository;

import com.varchar6.petcast.domain.review.command.domain.aggregate.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
