package com.example.corebasic.singleton;

import com.example.corebasic.AppConfig;
import com.example.corebasic.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;

public class SingletonTest {

    /**
     * jvm 메모리에 객체가 계속 생성되어서 올라갈 것이다.
     * 웹 애플리케이션은 요청이 올 때마다 객체를 생성하면 메모리 낭비가 심하다.
     * 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. 싱글톤 패턴
     */
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    @Test
    void pureContainer() throws Exception {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertNotSame(memberService1, memberService2);
    }
}
