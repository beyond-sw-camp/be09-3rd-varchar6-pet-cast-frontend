package com.varchar6.petcast.domain.proposalandestimate.command.domain.repository;

import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Proposals;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProposalsRepository extends JpaRepository<Proposals, Integer> {

}
