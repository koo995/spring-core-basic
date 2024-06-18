package com.example.corebasic.discount;

import com.example.corebasic.member.Grade;
import com.example.corebasic.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Primary 얘가 우선순위가 최상위로 잡혀서 의존관계 주입이 될 것이다.
 * 생각보다 많이 쓰인다. @Qualifier 은 지저분하게 많이 붙여야 한다.
 * 예를 들어 메인데이터베이스 보조 데이터베이스를 사용할때 룰을 정해서 쓰기도 한다
 */
@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP)
            return price * discountPercent / 100;
        else
            return 0;
    }
}
