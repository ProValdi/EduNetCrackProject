package com.ncedu.knownetimpl.security.config;

import com.ncedu.knownetimpl.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
      .cors().and()
      .csrf().disable()
      .authorizeRequests()
          .antMatchers("/").permitAll()
          .antMatchers("/auth/hello").permitAll()
          .antMatchers("/auth/login").permitAll()
            .antMatchers("/users").permitAll()
        .anyRequest()
        .authenticated()
      .and()
            .httpBasic()
      .and()
        .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID")
          .logoutSuccessUrl("/auth/hello").permitAll();
  }


  @Autowired
  public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
  }

}
