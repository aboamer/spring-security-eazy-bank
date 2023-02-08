package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    /**
     *
     * for the register endpoint to succeed for now, we need to disable csrf. this will be addressed later
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeHttpRequests().requestMatchers("/myAccount", "myBalance", "myLoans", "myCards").authenticated()
                .requestMatchers("/myNotices", "/myContacts", "/register").permitAll()
                .and().formLogin().and().httpBasic();
        return http.build();
    }

    /**
     *
     * commenting this because we are using db schema different from default one of spring security, implementing UserDetailsService
     *
     * Then, we removed the class implementing UserDetailsService to put our own authentication logic, that is more than validating user and pwd
     */
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
