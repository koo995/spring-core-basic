package com.example.corebasic.singleton;

import com.example.corebasic.AppConfig;
import com.example.corebasic.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    @Test
    void singletonServiceTest() throws Exception {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        /**
         * same 은 == 비교
         * equal 은 equals 비교
         * 여기서는 인스턴스를 비교할 것이기 때문에 same 을 사용한다.
         */
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    /**
     * 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용할 수 있다.
     * 이제 여기서 이전의 AppConfig 에서 스프링 컨테이너를 사용할때 어떤 장점이 있는지 하나 알 수 있다.
     * 1. 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다. AppConfig 에서 싱글톤을 만들기 위해선 코드가 많이 들어가야한다.
     */
    @DisplayName("스프링 컨테이너와 싱글톤")
    @Test
    void springContainer() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
