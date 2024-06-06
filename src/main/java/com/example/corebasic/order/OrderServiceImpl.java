package com.example.corebasic.order;

import com.example.corebasic.discount.DiscountPolicy;
import com.example.corebasic.discount.FixDiscountPolicy;
import com.example.corebasic.member.Member;
import com.example.corebasic.member.MemberRepository;
import com.example.corebasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /**
     * 우리는 역할과 구현을 충실하게 분리했다.
     * 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다.
     * OCP, DIP 같은 객체지향 설계 원칙을 준수했다.? -> 그렇게 보이지만 사실은 아니다.
     * 정책을 변경할려면 FixDiscountPolicy 를 RateDiscountPolicy 로 변경해야 한다.
     * 추상 인터페이스 뿐만 아니라, 구체 클래스에도 의존하고 있다. 둘다 의존하고 있다 -> DIP 위반
     * 이 정도면 훌륭한 것이지만... 할인 정책 변경인데 OrderServiceImpl 코드를 고쳐야 한다. -> OCP 위반 (기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다.)
     */
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
}
