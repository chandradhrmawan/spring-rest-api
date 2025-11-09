package com.domain.dto;

import lombok.AllArgsConstructor;

@lombok.Setter
@lombok.Getter
@AllArgsConstructor

public class SearchData {

  private String searchKey;
  private String otherSearchKey;

}
