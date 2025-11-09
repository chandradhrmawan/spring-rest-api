package com.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Category;
import com.domain.models.repository.CategoryRepository;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public Category saveOrUpdateCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Category findOne(Long id) {
    return categoryRepository.findById(id).orElse(null);
  }

  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }

  public void removeOne(Long id) {
    categoryRepository.deleteById(id);
  }
}
