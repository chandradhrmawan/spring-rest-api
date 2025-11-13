package com.domain.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.models.entities.AppUser;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

  Optional<AppUser> findByEmail(String email);

}
