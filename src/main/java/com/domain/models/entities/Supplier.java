package com.domain.models.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@lombok.Getter 
@lombok.Setter

@Entity
@Table(name = "suppliers")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Supplier implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Name Is Required")
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @NotEmpty(message = "Address Is Required")
  @Column(name = "address", nullable = false, length = 100)
  private String address;

  @NotEmpty(message = "Email Is Required")
  @Column(name = "email", nullable = false, length = 100, unique = true)
  private String email;

  @ManyToMany(mappedBy = "suppliers")
  @JsonBackReference
  Set<Product> product;

}
