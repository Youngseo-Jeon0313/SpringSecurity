package com.example.oauth2.config;

public class WebClientConfig {

    /* TODO
     * 기존 Java Spring의 RestTemplate은 Multi-Thread & Blocking 방식이었으나
     * WebClient를 사용해 Single Thread와 Non-Blocking 방식 사용
     *
     * 인증 서버와 Authorization Code 등을 통신&교환하는 과정 서버 사이드에서 이루어지도록 하기 위함
     * (비동기적인 방식으로 HTTP 리소스에 액세스 가능!)
     */

}
