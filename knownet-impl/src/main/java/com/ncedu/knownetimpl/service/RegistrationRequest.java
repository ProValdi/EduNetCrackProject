package com.ncedu.knownetimpl.service;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
  private final String firstName;
  private final String lastname;
  private final String email;
  private final String password;
}
