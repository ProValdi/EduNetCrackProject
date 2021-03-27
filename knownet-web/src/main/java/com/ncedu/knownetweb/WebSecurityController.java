package com.ncedu.knownetweb;

import com.ncedu.knownetimpl.model.entity.Role;
import com.ncedu.knownetimpl.model.entity.User;
import com.ncedu.knownetimpl.payload.JwtResponse;
import com.ncedu.knownetimpl.payload.LoginRequest;
import com.ncedu.knownetimpl.payload.MessageResponse;
import com.ncedu.knownetimpl.payload.SignupRequest;
import com.ncedu.knownetimpl.repository.UserRepository;
import com.ncedu.knownetimpl.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class WebSecurityController {
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    User userDetails = (User) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
      userDetails.getId(),
      userDetails.getUsername(),
      userDetails.getEmail(),
      Role.USER));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByLogin(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Username is already taken!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()));

    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
