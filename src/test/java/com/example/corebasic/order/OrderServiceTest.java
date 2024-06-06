package com.example.corebasic.order;

import com.example.corebasic.AppConfig;
import com.example.corebasic.member.Grade;
import com.example.corebasic.member.Member;
import com.example.corebasic.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @DisplayName("주문생성")
    @Test
    void createOrder() throws Exception {
        // given
        Long memberId = 1L; // long을 써도 되지만 나중에 null 이 들어갈 수 있어서?
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
