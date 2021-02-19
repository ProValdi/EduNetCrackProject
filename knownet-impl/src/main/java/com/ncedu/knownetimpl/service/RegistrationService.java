package com.ncedu.knownetimpl.service;

import com.ncedu.knownetimpl.model.entity.User;
import com.ncedu.knownetimpl.security.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final UserService userService;
  private EmailValidator emailValidator;

  public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.getEmail());

    if(!isValidEmail) {
      throw new IllegalStateException("email not valid");
    }
    return userService.signUpUser(new User(request.getPassword(),
                                           request.getFirstName(),
                                           request.getLastname(),
                                           request.getEmail()));

  }
}
