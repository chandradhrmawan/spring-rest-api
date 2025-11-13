package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.AppUserData;
import com.domain.dto.ResponseData;
import com.domain.models.entities.AppUser;
import com.domain.models.entities.AppUserRole;
import com.domain.services.AppUserService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

  @Autowired
  private AppUserService appUserService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/register")
  public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData appUserData) {

    ResponseData<AppUser> response = new ResponseData<>();
    // AppUser appUser = modelMapper.map(appUserData, AppUser.class);
    AppUser appUser = new AppUser();
    appUser.setFullName(appUserData.getFullName());
    appUser.setEmail(appUserData.getEmail());
    appUser.setPassword(appUserData.getPassword());
    appUser.setAppUserRole(AppUserRole.valueOf(appUserData.getAppUserRole()));

    response.setPayload(appUserService.registerAppUser(appUser));
    response.setStatus(true);
    response.getMessage().add("User Saved");
    return ResponseEntity.ok(response);

  }

}
