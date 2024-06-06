package com.example.corebasic.discount;

import com.example.corebasic.member.Grade;
import com.example.corebasic.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @DisplayName("vip는 10% 할인이 적용되어야 한다.")
    @Test
    void vip_o() throws Exception {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertEquals(discount, 1000);
    }

    @DisplayName("vip가 아니면 할인이 적용되지 않아야 한다.")
    @Test
    void vip_x() throws Exception {
        // given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertEquals(discount, 0);
    }

}