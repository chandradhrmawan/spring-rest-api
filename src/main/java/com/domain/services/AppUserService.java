package com.domain.services;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.models.entities.AppUser;
import com.domain.models.repository.AppUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppUserService {

  @Autowired
  private AppUserRepository appUserRepository;

  // @Autowired
  // private BCryptPasswordEncoder bCryptPasswordEncoder;

  // @Override
  // public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  //   // TODO Auto-generated method stub
  //   // throw new UnsupportedOperationException("Unimplemented method
  //   // 'loadUserByUsername'");
  //   AppUser appUser = appUserRepository.findByEmail(email).orElse(null);
  //   if (appUser == null) {
  //     throw new UsernameNotFoundException(
  //         String.format("user with email '%s' not found", email));
  //   }
  //   return loadUserByUsername(email);
  // }

  public AppUser registerAppUser(AppUser user) {

    boolean userExist = appUserRepository
        .findByEmail(user.getEmail())
        .isPresent();

    if (userExist) {
      throw new RuntimeException(
          String.format("User with email '%s' already exist", user.getEmail()));
    }

    // String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(user.getPassword());

    return appUserRepository.save(user);
  }

}
