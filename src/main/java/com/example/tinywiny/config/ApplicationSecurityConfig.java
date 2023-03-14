package com.example.tinywiny.config;


import com.example.tinywiny.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig {


  private final JwtFilter jwtFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .authorizeHttpRequests(requests -> requests
            .antMatchers("/", "/error", "/login", "/api/v1/login",
                "/api/v1/registration", "/authorization/login", "/api/v1/sessions", "/api/v1/products/**",
                "/api/v1/reviews").permitAll()
            .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "**/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**").permitAll()
            //.antMatchers("/api/v1/reviews").hasRole("USER")
            .anyRequest().authenticated()
        )
        .exceptionHandling(e -> e.authenticationEntryPoint
            (new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}


