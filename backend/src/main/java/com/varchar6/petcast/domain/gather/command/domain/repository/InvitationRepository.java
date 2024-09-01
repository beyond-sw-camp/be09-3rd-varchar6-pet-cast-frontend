package com.varchar6.petcast.domain.gather.command.domain.repository;

import com.varchar6.petcast.domain.invitation.command.domain.aggregate.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
}
