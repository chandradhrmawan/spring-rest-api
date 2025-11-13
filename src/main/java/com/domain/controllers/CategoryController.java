package com.domain.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.CategoryData;
import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.models.entities.Category;
import com.domain.services.CategoryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public ResponseEntity<ResponseData<Category>> create(
      @Valid @RequestBody CategoryData categoryData,
      Errors errors) {

    ResponseData<Category> responseData = new ResponseData<>();

    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessage().add(error.getDefaultMessage());
      }

      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    Category category = new Category();
    category.setName(categoryData.getName());

    responseData.setStatus(true);
    responseData.setPayload(categoryService.saveOrUpdateCategory(category));
    return ResponseEntity.ok(responseData);
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable Long id) {
    return categoryService.findOne(id);
  }

  @GetMapping
  public Iterable<Category> findAll() {
    return categoryService.findAll();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<CategoryData>> update(
      @PathVariable Long id,
      @Valid @RequestBody CategoryData categoryData,
      Errors errors) {

    ResponseData<CategoryData> responseData = new ResponseData<>();

    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessage().add(error.getDefaultMessage());
      }

      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    Category category = new Category();
    category.setName(categoryData.getName());

    responseData.setStatus(true);
    responseData.setPayload(categoryData);
    categoryService.saveOrUpdateCategory(category);

    return ResponseEntity.ok(responseData);

  }

  @PostMapping("/search/{size}/{page}/{sort}")
  public Iterable<Category> findByName(
      @RequestBody SearchData searchData,
      @PathVariable("size") int size,
      @PathVariable("page") int page,
      @PathVariable("sort") String sort) {

    Sort sortDirection = sort.equalsIgnoreCase("desc") ? Sort.by("id").descending() : Sort.by("id").ascending();

    Pageable pageable = PageRequest.of(page, size, sortDirection);

    return categoryService
        .findByName(searchData.getSearchKey(), pageable);
  }

  @PostMapping("/batch")
  public ResponseEntity<ResponseData<Iterable<Category>>> createBatch(@RequestBody Category[] categories) {

    ResponseData<Iterable<Category>> responseData = new ResponseData<>();
    responseData.setPayload(categoryService.saveBatch(Arrays.asList(categories)));

    responseData.setStatus(true);
    return ResponseEntity.ok(responseData);

  }

}
