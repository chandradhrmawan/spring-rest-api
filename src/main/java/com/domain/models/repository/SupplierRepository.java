package com.domain.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Supplier;
import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

  Supplier findByEmail(String email);

  List<Supplier> findByNameContains(String email);

  List<Supplier> findByNameStartingWith(String prefix);

  List<Supplier> findByNameContainsOrEmailContains(String name, String email);
}
