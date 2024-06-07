package com.example.corebasic.beanDefinition;

import com.example.corebasic.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    /**
     * 참고로 왜 ApplicationContext 가 아니라 AnnotationConfigApplicationContext 로 했냐면?
     * getBeanDefinition() 메서드가 AnnotationConfigApplicationContext 여기 정의되어있기 때문이다.
     */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    /**
     * 스프링이 다양한 형태의 설정 정보를 beanDefinition 으로 추상화해서 사용하는 것 정도만 이해하면 된다.
     **/
    @DisplayName("빈 설정 메타정보 확인")
    @Test
    void findApplicationBean() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        ", beanDefinition = " + beanDefinition);
            }
        }
    }


}
