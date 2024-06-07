package com.example.corebasic.beanfind;

import com.example.corebasic.AppConfig;
import com.example.corebasic.member.MemberService;
import com.example.corebasic.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("빈 이름으로 조회")
    @Test
    void findBeanByName() throws Exception {
        // given
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // when
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("이름 없이 타입으로만 조회")
    @Test
    void findBeanByType() throws Exception {
        // given
        MemberService memberService = ac.getBean(MemberService.class);

        // when
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     * 역할에 의존해야하는데... 이 테스트는 구체타입에 의존하여 조회하기에 좋은 것은 아니지만
     * 세상 어떤일이 일어날지 모르고 혹시나!
     */
    @DisplayName("구체 타입으로 조회")
    @Test
    void findBeanByName2() throws Exception {
        // given
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        // when
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("빈 이름으로 조회X")
    @Test
    void findBeanByNameX() throws Exception {
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
    }
}
