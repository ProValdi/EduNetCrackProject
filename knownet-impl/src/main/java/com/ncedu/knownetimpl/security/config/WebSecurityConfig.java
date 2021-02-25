package com.ncedu.knownetimpl.security.config;

import com.ncedu.knownetimpl.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


//@EnableWebSecurity
//@AllArgsConstructor
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true)
//@Configuration


@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
@AllArgsConstructor
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/").permitAll()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/auth/login").permitAll()
//            .defaultSuccessUrl("/auth/success")
//            .and()
//            .logout()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
//            .invalidateHttpSession(true)
//            .deleteCookies("JSESSIONID")
//            .logoutSuccessUrl("/auth/login")
//            .and().httpBasic();

    http.csrf().disable();


    http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/users/user").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic();
//
//
//    http
//            .formLogin()
//            .loginPage("/auth/login").permitAll()
//            .defaultSuccessUrl("/auth/success");
//
//    http
//            .logout()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
//            .invalidateHttpSession(true)
//            .deleteCookies("JSESSIONID")
//            .logoutSuccessUrl("/auth/login");

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/authFailure");
  }

//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth)
//          throws Exception {
//    auth.inMemoryAuthentication().withUser("user")
//            .password(passwordEncoder.encode("password")).roles("ADMIN");
//  }


  @Autowired
  public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
  }
}
