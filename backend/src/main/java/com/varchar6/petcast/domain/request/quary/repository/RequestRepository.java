package com.varchar6.petcast.domain.request.quary.repository;

import com.varchar6.petcast.domain.request.command.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
