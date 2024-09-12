package com.varchar6.petcast.serviceothers.domain.category.command.domain.repository;

import com.varchar6.petcast.serviceothers.domain.category.command.domain.aggregate.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsById(Integer categoryId);
}
