package com.example.koalaauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/* Spring Security 6.0 버전 기준 WebSecurityConfigurerAdapter 사용 불가
 * 대신 개발자가 직접 component-based security 설정을 할 수 있도록 변경됨 -> 커스텀할 설정들 등록하여 사용
 * 그럼 뭘로 대체됐냐? -> SecurityConfigurerAdapter
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests((authorize)-> authorize
                        .requestMatchers("/attend").hasRole("ADMIN")
                        .anyRequest().permitAll() //그 외에 다른 uri는 permitAll
                )
                .logout((logout)->logout.logoutUrl("/logout")
                        .deleteCookies("JSESSIONID") //로그아웃 후 쿠키 삭제
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/")
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}