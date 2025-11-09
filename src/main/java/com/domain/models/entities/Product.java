package com.domain.models.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

@lombok.Getter
@lombok.Setter
@NoArgsConstructor

@Entity
@Table(name = "products")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Name Is Required")
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "price", nullable = false)
  private Double price;

  @NotEmpty(message = "Description Is Required")
  @Column(name = "description", length = 255)
  private String description;

  @ManyToOne
  private Category category;

  @ManyToMany
  @JoinTable(name = "product_supplier", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
  @JsonManagedReference
  Set<Supplier> suppliers;

  // public Product() {
  // }

  // public Product(Long id, String name, Double price, String description) {
  // this.id = id;
  // this.name = name;
  // this.price = price;
  // this.description = description;
  // }

  // public static long getSerialversionuid() {
  // return serialVersionUID;
  // }

  // public Long getId() {
  // return id;
  // }

  // public void setId(Long id) {
  // this.id = id;
  // }

  // public String getName() {
  // return name;
  // }

  // public void setName(String name) {
  // this.name = name;
  // }

  // public Double getPrice() {
  // return price;
  // }

  // public void setPrice(Double price) {
  // this.price = price;
  // }

  // public String getDescription() {
  // return description;
  // }

  // public void setDescription(String description) {
  // this.description = description;
  // }

  // public Category getCategory() {
  // return category;
  // }

  // public void setCategory(Category category) {
  // this.category = category;
  // }

}
