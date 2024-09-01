package com.varchar6.petcast.domain.proposalandestimate.repository;

import com.varchar6.petcast.domain.request.aggregate.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateRepository extends JpaRepository<Request, Integer> {
}
