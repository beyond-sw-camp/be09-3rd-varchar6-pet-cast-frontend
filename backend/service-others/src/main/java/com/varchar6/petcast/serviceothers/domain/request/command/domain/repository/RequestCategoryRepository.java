package com.varchar6.petcast.serviceothers.domain.request.command.domain.repository;

import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity.RequestCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestCategoryRepository extends JpaRepository<RequestCategory, Integer> {

}
