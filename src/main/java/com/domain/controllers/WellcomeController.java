package com.domain.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/wellcome")
public class WellcomeController {

  @GetMapping
  public String welcome() {
    return "Wellcome";
  }

}
