package com.varchar6.petcast.domain.proposalandestimate.command.domain.repository;

import com.varchar6.petcast.domain.request.command.domain.aggregate.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimatesRepository extends JpaRepository<Requests, Integer> {
}
