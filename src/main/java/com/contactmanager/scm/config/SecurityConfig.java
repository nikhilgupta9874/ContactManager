package com.contactmanager.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.contactmanager.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // public UserDetailsService userDetailsService(){
    //     UserDetails user1 = User
    //     .withUsername("admin123")
    //     .password("admin123")
    //     .roles("ADMIN", "USER")
    //     .build();
    //     var inMemoryUserDetaisManager = new InMemoryUserDetailsManager(user1);
    //     return inMemoryUserDetaisManager; 
    // }
    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilerChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/home").permitAll();
        });
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
