package com.example.corebasic;

import com.example.corebasic.discount.DiscountPolicy;
import com.example.corebasic.discount.RateDiscountPolicy;
import com.example.corebasic.member.MemberRepository;
import com.example.corebasic.member.MemberService;
import com.example.corebasic.member.MemberServiceImpl;
import com.example.corebasic.member.MemoryMemberRepository;
import com.example.corebasic.order.OrderService;
import com.example.corebasic.order.OrderServiceImpl;

public class AppConfig {

    /**
     * 공연기획자가 배우를 설정한다.
     * 클라이언트인 OrderServiceImpl 입장에서 보면 의존관계를 마치 주입받는 것 같아서 DI(Dependency Injection) 의존성 주입이라 한다.
     * 리팩토링으로 어떤 장점이 있냐면 메서드 명을 보면 역할과 구현클래스가 한눈에 다 들어난다. 중복도 제거하였다.
     * 애플리케이션 전체 설계를 한눈에 알 수 있다.
     */
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
