package com.example.corebasic.discount;

import com.example.corebasic.member.Grade;
import com.example.corebasic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
