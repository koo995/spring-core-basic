package com.example.corebasic;

import com.example.corebasic.discount.FixDiscountPolicy;
import com.example.corebasic.member.MemberService;
import com.example.corebasic.member.MemberServiceImpl;
import com.example.corebasic.member.MemoryMemberRepository;
import com.example.corebasic.order.OrderService;
import com.example.corebasic.order.OrderServiceImpl;

public class AppConfig {

    /**
     * 공연기획자가 배우를 설정한다.
     * 클라이언트인 OrderServiceImpl 입장에서 보면 의존관계를 마치 주입받는 것 같아서 DI(Dependency Injection) 의존성 주입이라 한다.
     */
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
