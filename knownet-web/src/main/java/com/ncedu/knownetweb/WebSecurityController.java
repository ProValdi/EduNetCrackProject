package com.ncedu.knownetweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: доделать нормальные html

@RestController
@RequestMapping("/auth")
public class WebSecurityController {

  @GetMapping("/login")
  public String login() {
    return "\n" +
      "<!DOCTYPE html>\n" +
      "<html lang=\"en\">\n" +
      "\n" +
      "<head>\n" +
      "  <meta charset=\"utf-8\">\n" +
      "  <title>Login Custom</title>\n" +
      "</head>\n" +
      "\n" +
      "<body>\n" +
      "<div class=\"container\">\n" +
      "  <form class=\"form-signin\" method=\"post\" action=\"/auth/login\">\n" +
      "    <h2 class=\"form-signin-heading\">Login</h2>\n" +
      "    <p>\n" +
      "      <label for=\"username\" class=\"sr-only\">Username</label>\n" +
      "      <input type=\"text\" id=\"username\" name=\"username\" class=\"form-control\" placeholder=\"Username\" required >\n" +
      "    </p>\n" +
      "    <p>\n" +
      "      <label for=\"password\" class=\"sr-only\">Password</label>\n" +
      "      <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\" required>\n" +
      "    </p>\n" +
      "    <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign in</button>\n" +
      "  </form>\n" +
      "</div>\n" +
      "</body>\n" +
      "</html>";
  }

  @GetMapping("/success")
  public String success() {
    return "<!DOCTYPE html>\n" +
      "<html lang=\"en\">\n" +
      "<head>\n" +
      "  <meta charset=\"UTF-8\">\n" +
      "  <title>Title</title>\n" +
      "  <h1>Hello, Ivan. You're pidor</h1>\n" +
      "</head>\n" +
      "<body>\n" +
      "\n" +
      "</body>\n" +
      "</html>\n";
  }

  @GetMapping("/hello")
  public String hello() {
    return "<!DOCTYPE html>\n" +
      "<html lang=\"en\">\n" +
      "<head>\n" +
      "  <meta charset=\"UTF-8\">\n" +
      "  <title>Hello to the Knownet project</title>\n" +
      "  <h1>Hello to the Knownet project</h1>\n" +
      "  <a href=\"/login\">Click here for sign in</a>\n" +
      "</head>\n" +
      "<body>\n" +
      "\n" +
      "</body>\n" +
      "</html>";
  }
}
