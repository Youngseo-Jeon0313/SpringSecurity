package com.example.oauth2.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
    /* TODO
     * CustomFilter를 만들어
     * 기존 UsernamePasswordAuthenticationFilter보다 먼저 걸릴 수 있도록 설정해주어야 한다.
     * OncePerRequestFilter 확장
     */
}
