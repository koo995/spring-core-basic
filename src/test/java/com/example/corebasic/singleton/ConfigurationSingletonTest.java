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

        Assertions.assertThat(memberService.getMemberRepository()).isNotSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isNotSameAs(memberRepository);
    }

    @DisplayName("Configuration Deep Test")
    @Test
    void configurationDeep() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfigBean = ac.getBean(AppConfig.class);

        /**
         * @Configuration을 제거하였더니 순수한 AppConfig.class 가 나온다.
         * 실제로 스프링 빈으로는 등록되었으나... 싱글톤이 깨지는 것을 볼 수 있다.
         * memberRepository() 가 3번 호출되는 것을 볼 수 있다.
         * 1번은 @Bean 에 의해 스프링 빈에 등록하기 위함이고,
         * 나머지는 각각 memberService(), orderService() 에서 호출하기 때문이다.
         * 지금은 생짜로 호출하여... memberService(), orderService() 에서 호출한 녀석은 스프링 컨테이너가 관리하는 녀석이 아니다.
         * 그냥 하나의 객체를 생성한 것이다.
         */
        System.out.println("appConfigBean = " + appConfigBean.getClass());
    }
}
