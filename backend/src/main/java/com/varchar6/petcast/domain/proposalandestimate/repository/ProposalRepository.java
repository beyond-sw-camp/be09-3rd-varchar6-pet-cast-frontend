package com.varchar6.petcast.domain.proposalandestimate.repository;

import com.varchar6.petcast.domain.proposalandestimate.aggregate.Proposal;
import com.varchar6.petcast.domain.request.aggregate.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

}
