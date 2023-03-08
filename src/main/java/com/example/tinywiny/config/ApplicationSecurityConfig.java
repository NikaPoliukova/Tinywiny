package com.example.tinywiny.config;


import com.example.tinywiny.security.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig {

  private final JwtTokenFilter jwtTokenFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .authorizeRequests(a -> a
                .antMatchers("/", "/error", "/login", "/api/v1/login", "/api/v1/registration", "/authorization/login").permitAll()
//                        .antMatchers("/api/v1/user/*").hasRole("ADMIN")
                .anyRequest().permitAll()
        )
        .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtTokenFilter, OAuth2LoginAuthenticationFilter.class)
        .build();
  }

  /*
  private final JwtFilter jwtFilter;
  private final ExceptionHandlerFilter exceptionHandlerFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .authorizeRequests(a -> a
                .antMatchers("/", "/login","/api/v1/login", "/api/v1/registration",
                    "/api/v1/products/type/**").permitAll()
            .anyRequest().authenticated()
        )
        .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();

   */


       /* .cors()
        .and()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests(requests -> requests
            .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .antMatchers("/api/v1/login", "/api/v1/registration",
                "/api/v1/gallery", "/api/v1/products/type/**", "/api/v1/products/**",
                "/api/v1/reviews", "/api/v1/contacts").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
        .csrf().csrfTokenRepository(csrfTokenRepository())
        .and()
        .logout(LogoutConfigurer::permitAll)
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();*/
}

  /*private CookieCsrfTokenRepository csrfTokenRepository() {
    final CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
    repository.setCookieName(xsrfCookieName);
    repository.setHeaderName(xsrfHeaderName);
    repository.setCookieDomain(cookieDomain);
    return repository;*/



