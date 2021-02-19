package com.ncedu.knownetweb;

import com.ncedu.knownetimpl.service.RegistrationRequest;
import com.ncedu.knownetimpl.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class UserRegistrationController {

  private RegistrationService registrationService;

  @PostMapping
  public String register(@RequestBody RegistrationRequest request) {
    return registrationService.register(request);
  }
}
