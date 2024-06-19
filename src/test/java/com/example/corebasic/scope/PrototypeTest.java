package com.example.corebasic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    /**
     * 잘 보면 find prototypeBean1 이 출력된 후 init 이 출력되는 것을 볼 수 있다.
     * 빈을 조회할때 객체가 생성한 것이다. 프로토 타입 빈은 요청할 때 마다 새로 생성되기 때문이다.
     * 그렇기에 1번과 2번은 서로 다른 주소값으로 서로 다른 객체이며, destroy 도 호출되지 않는 것을 볼 수 있다.
     * 그냥 만들고 버린것. 프로토타입 빈은 스프링 컨테이너가 관리하는 것이 아니라 destroy 가 실행되지 않는 것이다.
     * 스프링 커테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
     * 만약에 destroy 을 호출할 필요가 있다면 직접 호출하면 된다.
     * prototypeBean1.destroy()
     */
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);


        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();

    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
