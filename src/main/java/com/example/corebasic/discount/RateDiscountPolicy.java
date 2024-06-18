package com.example.corebasic.discount;

import com.example.corebasic.annotation.MainDiscountPolicy;
import com.example.corebasic.member.Grade;
import com.example.corebasic.member.Member;
import org.springframework.stereotype.Component;

/**
 * @Qualifier 은 문자열로 이름을 정하기때문에 컴파일시에 오류를 잡을 수 없다.
 * 애너테이션을 새롭게 만들면 컴파일 오류로 잡을 수 있다.
 */
@Component
@MainDiscountPolicy
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
