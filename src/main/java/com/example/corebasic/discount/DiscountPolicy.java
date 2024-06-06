package com.example.corebasic.discount;

import com.example.corebasic.member.Member;

public interface DiscountPolicy {


    int discount(Member member, int price);
}
