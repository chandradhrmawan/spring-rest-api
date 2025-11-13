package com.domain.models.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.domain.models.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Page<Category> findByNameContains(String name, Pageable pageable);

}
