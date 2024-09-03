package com.varchar6.petcast.domain.proposalandestimate.repository;

import com.varchar6.petcast.domain.proposalandestimate.aggregate.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

}
