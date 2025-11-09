package com.domain.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;

import jakarta.websocket.server.PathParam;

public interface ProductRepository extends CrudRepository<Product, Long> {

  @Query("SELECT p FROM Product p WHERE p.name = :name")
  Product findProductByName(@PathParam("name") String name);

  @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
  List<Product> findProductByNameLike(@PathParam("name") String name);

  @Query("SELECT p FROM Product p WHERE p.category.id =:categoryId")
  List<Product> findProductByCategoryId(@PathParam("categoryId") Long categoryId);

  @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
  public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);

}