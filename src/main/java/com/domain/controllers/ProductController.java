package com.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {

    ResponseData<Product> responseData = new ResponseData<>();

    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessage().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    responseData.setStatus(true);
    responseData.setPayload(productService.createOrUpdateProduct(product));
    return ResponseEntity.ok(responseData);
  }

  @GetMapping
  public Iterable<Product> findAll() {
    return productService.findAll();
  }

  @GetMapping("/{id}")
  public Product findOne(@PathVariable Long id) {
    return productService.findOne(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<Product>> update(
      @Valid @PathVariable Long id,
      @Valid @RequestBody Product product,
      Errors errors) {

    ResponseData<Product> responseData = new ResponseData<>();

    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessage().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    product.setId(id);
    responseData.setStatus(true);
    responseData.setPayload(productService.createOrUpdateProduct(product));

    return ResponseEntity.ok(responseData);
  }

  @DeleteMapping("/{id}")
  public void deleteOne(@PathVariable Long id) {
    productService.deleteProduct(id);
  }

  @PostMapping("/{productId}")
  public void addSupplier(
      @RequestBody Supplier supplier,
      @PathVariable Long productId) {
    productService.addSupplier(supplier, productId);
  }

  @PostMapping("/search/name")
  public Product getProductByName(@RequestBody SearchData searchData) {
    return productService.findByName(searchData.getSearchKey());
  }

  @PostMapping("/search/like")
  public List<Product> getProductByNameLike(@RequestBody SearchData searchData) {
    return productService.findByNameLike(searchData.getSearchKey());
  }

  @GetMapping("/search/category/{categoryId}")
  public List<Product> getProductByCategoryId(@PathVariable Long categoryId) {
    return productService.findByCategoryId(categoryId);
  }

  @GetMapping("/search/supplier/{supplierId}")
  public List<Product> getProductBySupplierId(@PathVariable Long supplierId) {
    return productService.findBySupplier(supplierId);
  }
}
