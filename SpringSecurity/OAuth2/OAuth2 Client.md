### OAuth2 Client
클라이언트는 사용자의 권한을 얻고 보호된 리소스에 대한 요청을 수행하는 주체   

액세스 토큰을 요청하거나/응답하는 형식을 사용자 정의할 수 있습니다.   
예를 들어, 추가적인 사용자 정보나 특정 속성을 액세스 토큰 응답에 포함할 수 있습니다.      
ex. Converter<OAuth2RefreshTokenGrantRequest, MultiValueMap<String, String>>   

최초 로그인 시 OAuth2 인증을 통해 우리의 서버에 접근할 수 있는 JWT토큰을 발급   
-> 인증 토큰이 만료되었을 경우 리프레쉬에 사용할 RefreshToken 도 함께 사용 가능