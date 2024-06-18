package com.example.corebasic.order;

import com.example.corebasic.discount.DiscountPolicy;
import com.example.corebasic.member.Member;
import com.example.corebasic.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * @Autowired 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
     */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
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
