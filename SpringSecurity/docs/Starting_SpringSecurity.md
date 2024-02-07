### Runtime에 우리가 기대하는 것
- 모든 엔드포인트(/error 포함)에 대해 사용자 인증 필요
- BCrypt 및 기타 암호 스토리지
- 폼 기반 로그인 및 로그아웃 흐름 
- 폼 기반 로그인 및 HTTP Basic 인증
- 인증 불가 시 401 Unauthorized 
- CSRF 공격 보호
- Session 공격 보호
- 스니핑 공격 완화를 위해 X-Content-Type-Options 작성
- 인증된 리소스를 보호하기 위해 Cache Control header 사용

### 시작 !
```java
@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {
    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        String generatedPassword = // ...;
        return new InMemoryUserDetailsManager(User.withUsername("user")
                .password(generatedPassword).roles("ROLE_USER").build());
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }
}
```
1. @EnableWebSecurity 어노테이션을 추가하세요 
2. user라는 이름을 가진 UserDetailService 를 빈으로 등록하고 password를 랜덤으로 만들어보세요
3. 인증 이벤트를 위해  AuthenticationEventPublisher 빈을 등록하세요.
AuthenticationEventPublisher는 AuthenticationSuccessEvent or AuthenticationFailureEvent 를 listen하기 위해 발행해야 한다 !
AuthenticationSuccessHandler , AuthenticationFailureHandler 같은 느낌임

```java
@Bean
public AuthenticationEventPublisher authenticationEventPublisher
        (ApplicationEventPublisher applicationEventPublisher) {
    return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
}
//이렇게 먼저 발행해놓고

@Component
public class AuthenticationEvents {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        // ...
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        // ...
    }
}
```

### Security를 어떤 의도로 쓸 건지 파악해라 !
- REST API와 JWT / Bearer Token
- API Gateway 또는 BFF
- Oauth 2.0 또는 OIDC
- SAML 2.0
...
- 인증?
- 보안?