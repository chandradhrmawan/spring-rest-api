package com.domain.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@lombok.Getter
@lombok.Setter

@Entity
@Table(name = "users")
public class AppUser implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 150, nullable = false)
  private String fullName;

  @Column(length = 150, nullable = false, unique = true)
  private String email;

  @Column(length = 200, nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private AppUserRole appUserRole;

}
