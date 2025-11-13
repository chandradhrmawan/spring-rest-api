package com.domain.utils;

import org.springframework.data.domain.AuditorAware;

import com.domain.models.entities.AppUser;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public java.util.Optional<String> getCurrentAuditor() {
    // In a real application, you would fetch the currently logged-in user
    // For simplicity, we return a fixed username here
    // return java.util.Optional.of("system_user");
    AppUser currAppUser = new AppUser();
    return java.util.Optional.of(currAppUser.getEmail());
  }

}
