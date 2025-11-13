package com.domain.dto;

import lombok.AllArgsConstructor;

// @lombok.Setter
// @lombok.Getter
// @AllArgsConstructor

@lombok.Data
@lombok.NoArgsConstructor

public class AppUserData {

  private String fullName;
  private String email;
  private String password;
  private String appUserRole;

}
