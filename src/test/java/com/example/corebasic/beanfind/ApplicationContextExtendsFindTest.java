package com.example.corebasic.beanfind;

import com.example.corebasic.discount.DiscountPolicy;
import com.example.corebasic.discount.FixDiscountPolicy;
import com.example.corebasic.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @DisplayName("부모타입으로 조회시 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    @Test
    void findBeanByParentTypeDuplicate() throws Exception {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @DisplayName("부모타입으로 조회시 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    @Test
    void findBeanByParentTypeBeanName() throws Exception {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @DisplayName("특정 하위 타입으로 조회")
    @Test
    void findBeanBySubType() throws Exception {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @DisplayName("부모타입으로 모두 조회하기")
    @Test
    void findAllBeanByParentType() throws Exception {
        assertThat(ac.getBeansOfType(DiscountPolicy.class)).hasSize(2);
    }

    /**
     * 한가지 궁금한 것은 왜 BeanDefinition 으로 조회했을때보다 더 많은 bean 이 나타나는 것이지?
     * 일부 빈은 내부적으로 사용하는 빈이기 때문에 BeanDefinition 으로 조회했을때는 나타나지 않는다.
     */
    @DisplayName("부모타입으로 모두 조회하기 - Object")
    @Test
    void findAllBeanByObjectType() throws Exception {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }



    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
