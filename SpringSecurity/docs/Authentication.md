# authentication(인증)
- 너 누구니? 확인하는 절차
- 인증은 사용자 또는 시스템이 자신이 주장하는 신원(Identity)이 실제로 누구인지 확인하는 과정입니다.
- 주로 아이디와 비밀번호, 생체 인식 (지문, 홍채, 얼굴 인식 등), 토큰, 인증서 등의 방법을 사용하여 신원을 확인합니다.
- 인증이 성공하면 사용자나 시스템은 자신이 주장하는 신원이 확인되었다는 것을 증명합니다.


---

# 암호 저장 역사
- 초기에는 암호가 일반 텍스트로 저장됨
- 그런데 악의적인 사용자들이 SQL Injection 같은 공격으로 데이터 dump 탈취
- SHA-256과 같은 단방향 해시 사용
- 악의적인 사용자들이 레인보우 테이블이라는 조회 테이블을 만들어, 암호를 추측하지 않고 한 번 계산한 후 조회 테이블에 저장
- salted password라는, 비밀번호에 무작위 바이트를 하나 더 만들고 해시 생성하는 방법으로 극복
- 하지만 현대에 암호해시는 안전하지 않음. 현대 하드웨어로 초당 수십억 개의 해시 연산이 가능하기 때문!
- 따라서 개발자들은 이제 salt 방법 같은 '적응형 단방향 함수'를 활용하여 암호를 저장하도록 권장됨.

## passwordEncoder
위의 역사에 따라,    
PasswordEncoder 의 형식이 단순 plain text 방식에서 DelegatingPasswordEncoder 형식으로 변화
```java
PasswordEncoder passwordEncoder =
    PasswordEncoderFactories.createDelegatingPasswordEncoder();
```

## Password Storage Format (암호 저장 형식)
```java
{id}encodedPassword

ex>
{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
{noop}password
{pbkdf2}5d923b44a6d129f3ddf3e3c8d29412723dcbde72445e8ef6bf3b508fbf17fa4ed4d6b99ca763d8dc
{scrypt}$e0801$8bWJaSu2IKSn9Z9kM+TPXfOc/9bdYSrN1oD9qfVThWEwdRTnO7re7Ei+fUZRJ68k9lTyuTeUp4of4g24hHnazw==$OAOec05+bXxvuu/1qZ6NUR+xQYvYv7BeL1QxwRpY5Pc=
{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0
```

## Password Matching
{id}를 기반으로 가장 최신 암호 인코딩을 사용하여 암호를 인코딩한다.

## withDefaultPasswordEncoder 예시
```java
UserDetails user = User.withDefaultPasswordEncoder()
  .username("user")
  .password("password")
  .roles("user")
  .build();
System.out.println(user.getPassword());
// {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
```

## BCryptPasswordEncoder를 사용해 인코딩 예제
```java
// Create an encoder with strength 16
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
String result = encoder.encode("myPassword");
assertTrue(encoder.matches("myPassword", result));
```
이 외에도 SCryptPasswordEncoder, Pbkdf2PasswordEncoder 등등 많다.

## 그럼 Spring Security는 Password Storage Configuration을 어떻게 쓸까?
비밀번호를 담아두기 위해 DelegatingPasswordEncoder를 기본적으로 쓴다.
비밀번호를 바꾸고 싶다면 default change password endpoint 사용 가능
```java
http
    .passwordManagement(Customizer.withDefaults()) //기본 패스워드 관리 설정
        
http
        .passwordManagement((management) -> management //사용자가 패스워드 변경할 때 사용할 페이지 -> update-password
        .changePasswordPage("/update-password")
        )
```

