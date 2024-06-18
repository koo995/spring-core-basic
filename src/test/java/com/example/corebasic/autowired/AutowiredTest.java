package com.example.corebasic.autowired;

import com.example.corebasic.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        /**
         * Member 은 스프링 빈이 아니다.
         * 첫번째 녀석은 의존관계 주입할 대상이 없으면 수정자 호출자체가 안된다.
         * 두번째 녀석은 호출은 되지만 null 이 들어간다.
         * 세번째 녀석도 호출은 되고 Optional.empty 가 들어간다.
         */

    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
