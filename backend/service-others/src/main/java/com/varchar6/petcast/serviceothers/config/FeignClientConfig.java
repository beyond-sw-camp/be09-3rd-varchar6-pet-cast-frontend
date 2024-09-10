package com.varchar6.petcast.serviceothers.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor(){
            @Override
            public void apply(RequestTemplate requestTemplate) {

                /* 설명. 현재 요청의 Http Servelet Request를 가져옴 */
                ServletRequestAttributes requestAttributes =
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

                if(requestAttributes != null){
                    /* 설명. 현재 요청의 Authorization 헤더 추출(Bearer 토큰) */
                    String authorizationHeader = requestAttributes
                            .getRequest() //request 객체 추출
                            .getHeader(HttpHeaders.AUTHORIZATION);

                    if(authorizationHeader != null){
                        requestTemplate.header(HttpHeaders.AUTHORIZATION, authorizationHeader);
                    }
                }
            }
        };
    }
}
