package com.ncedu.knownetweb;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: доделать нормальные html

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class WebSecurityController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/success")
  public String success() {
    return "success";
  }

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }
}
