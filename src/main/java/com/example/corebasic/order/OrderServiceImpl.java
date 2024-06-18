package com.example.corebasic.order;

import com.example.corebasic.discount.DiscountPolicy;
import com.example.corebasic.member.Member;
import com.example.corebasic.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /**
     * final 키워드가 있으면 값이 반드시 있어야 한다. 언어적으로 필수적으로 초기화를 제한할 수 있다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * 생성자 주입의 특정.
     *     1. 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다. -> 한번 세팅된 후 그 이후 변경되는 것을 막을 수 있다.
     *     2. 불변, 필수 의존관계에 사용
     *     3. 생성자가 딱 1개만 있다면, @Autowired 을 생략할 수 있다. -> ex) 기본생성자가 더 있으면 생략불가
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 단일 책임 원칙을 잘 지키고 있다.
     * orderService 는 주문 생성에 대한 책임만 가지고 있다.
     * 할인에 대한 책임은 discountPolicy 에게 위임하고 있다.
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
