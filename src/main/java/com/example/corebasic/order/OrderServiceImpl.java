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
     * @Qualifier 은 생성자, 수정자, 필드 모두에 사용가능하다. 그런데 만약 못찾으면? 정해진 이름의 빈을 추가로 찾는다.
     * 하지만 @Qualifier 은 @Qualifier 을 찾는 용도로만 사용하는게 명확하고 좋다.
     * @Primary 는 기본값처럼 동작하고 @Qualifier 은 매우 상세하게 동작한다. 만약 두개가 겹치면 @Qualifier 가 작동한다.
     */
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
