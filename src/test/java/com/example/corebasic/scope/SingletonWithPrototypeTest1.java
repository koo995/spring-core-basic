package com.example.corebasic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    /**
     * 만약 우리의 의도가 이 로직을 호출할 때마다 이 프로토타입 빈을 항상 새로 만들어 쓰고 싶다면 의도한 것과 다르다
     * 프로토타입 빈을 사용한다는 것은 사용할때 그냥 계속 새로 만들어서 쓰고 싶어서 쓰는거지 이런 경우면 그냥 싱글톤을 쓰는것과 다를 바 없다.
     * 그러면 어떻게 해야할까? 제일 무식한 방법은 ac 을 주입받아서 매번 요청하는 것이다.
     * 하지만 너무 지저분하다... 너무 스프링에 의존적이다.
     * 이러한 방식은 의존관계를 외부에서 주입 받는게 아니라 이렇게 직접 필요한 의존관계를 찾는 것을 Dependency Lookup 이라 한다.
     * provider 을 사용하는 방법으로 서로 다른 prototypeBean 을 가져오는 것을 볼 수 있다. 하지만 이녀석도 스프링에 의존적이다.
     */
    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy" + this);
        }
    }
}
