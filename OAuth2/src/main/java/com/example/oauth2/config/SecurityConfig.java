package com.example.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                //Websecurity 설정 필요
                .authorizeHttpRequests((authorize)->authorize
                        .requestMatchers("/home", "/login").permitAll()
                        .anyRequest().authenticated())
                // ...
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); //UsernamePasswordAuthenticationFilter 전에 수행할 것이다.

        return http.build();

    }


}
