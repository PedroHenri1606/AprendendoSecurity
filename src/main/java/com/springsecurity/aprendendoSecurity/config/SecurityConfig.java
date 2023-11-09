package com.springsecurity.aprendendoSecurity.config;

import com.springsecurity.aprendendoSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserService service;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/livre").permitAll()
                        .anyRequest().authenticated());

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    static PasswordEncoder psEncode(){
       return new BCryptPasswordEncoder();
    }


    protected void autenticador(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }
}
