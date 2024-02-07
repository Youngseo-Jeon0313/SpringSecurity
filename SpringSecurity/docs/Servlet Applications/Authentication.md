# 인증 메커니즘 종류
- Username and Password - 사용자 이름/비밀번호로 인증하는 방법
- OAuth 2.0 
- SAML 2.0 로그인
- CAS(Central Authentication Server) 
- Remember Me - 사용자의 과거 세션 만료를 기억하는 방법
- JAAS 인증
- 사전 인증 시나리오
SiteMinder 또는 Java EE 보안과 같은 외부 메커니즘으로 인증하지만 일반적인 공격으로부터 권한을 부여하고 보호하기 위해 여전히 Spring Security를 사용합니다.
- X509 인증 - X509 인증

### SecurityContextHolder
Spring Security가 '인증된 사람의 세부정보를 저장하는 곳'
![img.png](../images/img5.png)