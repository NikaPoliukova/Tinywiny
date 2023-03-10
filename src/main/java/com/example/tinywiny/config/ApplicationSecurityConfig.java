package com.example.tinywiny.config;


import com.example.tinywiny.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig {

  private final JwtFilter jwtTokenFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .authorizeHttpRequests(requests -> requests
            .antMatchers("/", "/error", "/login", "/api/v1/login",
                "/api/v1/registration", "/authorization/login").permitAll()
            //.antMatchers("/api/v1/reviews").hasRole("USER")
            .anyRequest().authenticated()
        )
//        .exceptionHandling(e -> e.authenticationEntryPoint
//            (new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}


