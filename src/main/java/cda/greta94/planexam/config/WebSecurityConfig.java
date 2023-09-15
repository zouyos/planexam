package cda.greta94.planexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout-> logout
                        .logoutUrl("/logout")
                        .permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/inscription","/login", "/css/**", "/js/**", "/img/**", "/favicon.ico", "/webjars/**").permitAll()
                        .requestMatchers(HttpMethod.POST,("/inscription")).permitAll()
                        .requestMatchers(HttpMethod.POST,("/admin/epreuve/jour/**")).permitAll()
                        //Interdit la page si l'utilisateur n'est pas admin
                        .requestMatchers("/admin/**").hasAuthority("admin")
                        .requestMatchers("/prof/**").hasAnyAuthority("prof","admin")
                        .anyRequest().authenticated()
                )
                .build();
    }

}
