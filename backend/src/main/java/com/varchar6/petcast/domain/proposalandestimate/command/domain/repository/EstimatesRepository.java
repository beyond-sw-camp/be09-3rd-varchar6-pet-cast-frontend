package com.varchar6.petcast.domain.proposalandestimate.command.domain.repository;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimatesRepository extends JpaRepository<Estimates, Integer> {
}
