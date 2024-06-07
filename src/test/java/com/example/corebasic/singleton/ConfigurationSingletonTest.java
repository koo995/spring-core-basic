package com.example.corebasic.singleton;

import com.example.corebasic.AppConfig;
import com.example.corebasic.member.MemberRepository;
import com.example.corebasic.member.MemberServiceImpl;
import com.example.corebasic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @DisplayName("Configuration Test")
    @Test
    void configurationTest() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        /**
         * 원래는 이렇게 구체타입으로 꺼내면 안되지만... test 용으로 만든 메서드를 사용하기 위함.
         * 결과를 보면 알겠지만 3놈다 같은 놈이다. (싱글톤이 보장된다.)
         */
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @DisplayName("Configuration Deep Test")
    @Test
    void configurationDeep() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfigBean = ac.getBean(AppConfig.class);

        /**
         * getClass() 호출하면 클래스 타입이 나온다.
         * AppConfig$$SpringCGLIB$$0
         * 내가 만든 클래스가 아니다.
         * AppConfig 라는 클래스를 상속받은
         * 임의의 다른 클래스를 만들어서(스프링이 CGLIB 라는 바이트코드 조작 라이브러리 이용하여) 그 클래스를 스프링이 내부에서 사용하는 것이다.
         * @Bean 이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
         * 이 덕분에 싱글톤이 보장되도록 해준다.
         * @Configuration 을 붙이면 바이트 코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장하지만, 만약 @Bean 만 적용하면 어떻게 될까? (@Configuration 을 안 붙이면?)
         * @Bean 만 적용하면 싱글톤을 보장하지 않는다. 그냥 스프링 빈으로 등록되는 것이다.
         */
        System.out.println("appConfigBean = " + appConfigBean.getClass());
    }
}
