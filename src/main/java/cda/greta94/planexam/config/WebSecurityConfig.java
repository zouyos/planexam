package cda.greta94.planexam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService; // Service pour gérer les détails des utilisateurs

  @Autowired
  private JwtAuthFilter jwtAuthFilter; // Filtre d'authentification basé sur JWT

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity
          .csrf(csrf -> csrf.disable())
          .formLogin(form -> form
                      .loginPage("/login")
                      .permitAll()
              )
              .logout(logout-> logout
                      .logoutUrl("/logout")
                      .permitAll()
              )
              .authorizeHttpRequests(auth -> auth
                      .requestMatchers("/","/inscription", "/login", "/forgot-password", "/reset-password/**", "/css/**", "/js/**", "/img/**", "/favicon.ico", "/webjars/**", "/api/**").permitAll()
                      .requestMatchers(HttpMethod.POST,("/inscription")).permitAll()
                      //Interdit la page si l'utilisateur n'est pas admin
                      .requestMatchers("/admin/**").hasAuthority("admin")
                      .requestMatchers("/prof/**").hasAnyAuthority("prof","admin")
                      .anyRequest().authenticated()
              )
              .build();
  }

  @Bean
  public PasswordEncoder getEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager(); // Configure le gestionnaire d'authentification
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    // Permet d'accéder à la console H2 sans authentification (utile pour le développement)
  }

  private AuthenticationProvider authenticationProvider() {
    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(getEncoder());
    return authenticationProvider; // Configure le fournisseur d'authentification pour l'application
  }
}
