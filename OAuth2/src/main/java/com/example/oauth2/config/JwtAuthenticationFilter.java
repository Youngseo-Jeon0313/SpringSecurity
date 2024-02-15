package com.example.oauth2.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /*
    인증 Header에 특정한 형식(Oauth2.0 Bearer Token)을 넣기 위해 CustomFilter 추가
    Filter 인터페이스 구현 대신 OncePerRequestFilter를 확장한다.
     */

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /*
        1. Request Header 에서 토큰을 꺼냄
        2. validateToken 으로 토큰 유효성 검사
          ->  정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        3. 토큰을 꺼낸 후 토큰 속 정보를 꺼내오기 (여기에서 할지 TokenService에서 할지 결정할 것 !)
         */

    }
}
