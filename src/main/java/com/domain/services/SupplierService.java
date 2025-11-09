package com.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Supplier;
import com.domain.models.repository.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {

  @Autowired
  private SupplierRepository supplierRepository;

  public Supplier saveOrUpdatSupplier(Supplier supplier) {
    return supplierRepository.save(supplier);
  }

  public Supplier findOne(Long id) {
    return supplierRepository.findById(id).orElse(null);
  }

  public Iterable<Supplier> findAll() {
    return supplierRepository.findAll();
  }

  public void remove(Long id) {
    supplierRepository.deleteById(id);
  }

  public Supplier findSupplierByEmail(String email) {
    return supplierRepository.findByEmail(email);
  }

  public List<Supplier> findSupplierByName(String email) {
    return supplierRepository.findByNameContains(email);
  }

  public List<Supplier> findByNameStarting(String prefix) {
    return supplierRepository.findByNameStartingWith(prefix);
  }

  public List<Supplier> findByNameOrEmail(String name, String email) {
    return supplierRepository.findByNameContainsOrEmailContains(name, email);
  }

}
