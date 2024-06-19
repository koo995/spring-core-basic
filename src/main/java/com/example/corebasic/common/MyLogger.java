package com.example.corebasic.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 동시에 여러 http 요청이 오면 정확히 어떤 요청이 남긴 로그인지 구분하기 어렵다.
 * 이럴때 사용하기 딱 좋은 것이 바로 request 스코프이다.
 * http 요청 당 하나씩 생성되고, http 요청이 끝나는 시점에 소멸된다.
 */
@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;

    @Setter
    private String requestURL;

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    /**
     * 이 빈이 생성되는 시점에(원래라면 의존관계 주입이 끝난 후) 자동으로 @PostConstruct 초기화 메서드를 사용해서
     * uuid 를 생성해서 저장해둔다.
     */
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " +this);
    }



}
