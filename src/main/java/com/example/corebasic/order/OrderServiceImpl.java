package com.example.corebasic.order;

import com.example.corebasic.discount.DiscountPolicy;
import com.example.corebasic.member.Member;
import com.example.corebasic.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /**
     * 필드 주입은
     * 의존관계에다 그냥 넣어버리는 것이다. private 라도 가능하다.
     * 코드가 간결하다. 하지만 안티페턴이다.
     * 그러나 외부에서 변경이 불가능하여 테스트하기가 어렵다. -> 테스트할때 mock 객체를 이용해야 하는 경우 이 값을 바꿀 수 없다. 방법에 전혀없다.
     * 넣을려면 setter 을 따로 만들어줘야한다. <- 그럴바에야 그냥 setter 에 @Autowired? 아예 그냥 이상하게 된다.
     * 결론적으로 di 프레임워크가 없으면 아무것도 할 수 없다. 모든것을 컨테이너에서 가져와야 한다.
     * 하지만 애플리케이션 코드와 관계없는 테스트에서는 사용하면 좋다. (또는 @Configuration 에서만..?)
     */
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;

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
