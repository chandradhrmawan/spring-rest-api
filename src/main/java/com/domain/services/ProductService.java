package com.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private SupplierService supplierService;

  public Product createOrUpdateProduct(Product product) {
    return productRepository.save(product);
  }

  public Product findOne(Long id) {
    return productRepository.findById(id).orElse(null);
  }

  public Iterable<Product> findAll() {
    return productRepository.findAll();
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  public Product findByName(String name) {
    return productRepository.findProductByName(name);
  }

  public void addSupplier(Supplier supplier, Long productId) {
    Product product = findOne(productId);
    if (product == null) {
      throw new RuntimeException("Product Not Found");
    }

    product.getSuppliers().add(supplier);
    createOrUpdateProduct(product);
  }

  public List<Product> findByNameLike(String name) {
    return productRepository.findProductByNameLike("%" + name + "%");
  }

  public List<Product> findByCategoryId(Long categoryId) {
    return productRepository.findProductByCategoryId(categoryId);
  }

  public List<Product> findBySupplier(Long supplierId) {
    Supplier supplier = supplierService.findOne(supplierId);
    if (supplier == null) {
      return new ArrayList<Product>();
    }
    return productRepository.findProductBySupplier(supplier);
  }

}
