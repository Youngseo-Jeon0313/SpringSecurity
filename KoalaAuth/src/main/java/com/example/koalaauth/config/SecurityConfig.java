package com.example.koalaauth.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/* Spring Security 6.0 버전 기준 WebSecurityConfigurerAdapter 사용 불가
* 대신 개발자가 직접 component-based security 설정을 할 수 있도록 변경됨 -> 커스텀할 설정들 등록하여 사용
* 그럼 뭘로 대체됐냐? -> SecurityConfigurerAdapter
* */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated();
        http
                .formLogin(form -> form
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/home") // 로그인 성공 후 이동 페이지 default
                        .failureUrl("/loginerror") //로그인 실패 후 이동 페이지
                        .usernameParameter("username") //아이디 파라미터명
                        .passwordParameter("password") //패스워드 파라미터명
                        .loginProcessingUrl("/login") //로그인 Form Action Url
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                System.out.println("authentication"+authentication.getName());
                                response.sendRedirect("/");
                            }
                        }) //로그인 성공 후 핸들러
                        .failureHandler(new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                System.out.println("exception"+exception.getMessage());
                                response.sendRedirect("/login");
                            }
                        }) //로그인 실패 후 핸들러
                        .permitAll() //loginPage로 접근하는 유저들은 모두 접근 가능
                );
        return http.build();
    }

}