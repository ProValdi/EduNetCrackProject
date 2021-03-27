package com.ncedu.knownetimpl.security.config;

import com.ncedu.knownetimpl.security.jwt.AuthEntryPointJwt;
import com.ncedu.knownetimpl.security.jwt.AuthTokenFilter;
import com.ncedu.knownetimpl.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserService userService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
      .antMatchers("/api/test/**").permitAll()
      .anyRequest().authenticated();

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//
////    http
////      .cors().and()
////      .csrf().disable()
////      .authorizeRequests()
////      .antMatchers("/").permitAll()
////      .antMatchers("/auth/hello").permitAll()
////      .antMatchers("/auth/login").permitAll()
////      .antMatchers("/users").permitAll()
////      .anyRequest()
////      .authenticated()
////      .and()
////      .httpBasic()
////      .and()
////      .logout()
////      .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
////      .invalidateHttpSession(true)
////      .deleteCookies("JSESSIONID")
////      .logoutSuccessUrl("/auth/hello").permitAll();
////  }
////
////
////  @Autowired
////  public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
////    auth
////      .userDetailsService(userService)
////      .passwordEncoder(passwordEncoder);
////  }

}
