package com.domain.dto;

import jakarta.validation.constraints.NotEmpty;

@lombok.Data
@lombok.NoArgsConstructor

public class CategoryData {
  @NotEmpty(message = "Name Is Required")
  private String name;
}
