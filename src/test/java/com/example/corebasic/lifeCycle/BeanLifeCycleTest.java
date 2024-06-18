package com.example.corebasic.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        /**
         * close() 때문에 ConfigurableApplicationContext 을 사용한다?
         * 잘 보면 url 이 세팅이 안된다. 객체를 생성할때는 url 이 null 은 맞다. url 정보가 안들어왔으니까
         * 객체를 생성한 다음에 외부에서 수정자 주입을 통해서 url이 존재하게 된다.
         * 스프링 빈은 "간단하게" 다음과 같은 라이프사이클을 가진다.
         * 객체 생성 -> 의존관계 주입
         * 스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다. 따라서 초기화 작업은
         * 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다. 그런데 개발자가 의존관계 주입이 모두 완료된 시점을 어떻게 알 수 있을까?
         * 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공한다.
         * 또한 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다. 따라서 안전하게 종료 작업을 진행할 수 있다.
         * 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
         * 참고: 객체의 생성과 초기화를 분리하자.
         * 생성자안에서는 필드값 정도만 주입받고 무거운 초기화작업(외부 커넥션 연결)은 함께 초기화하는 것보다는 분리하자.
         */
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /**
         * 아주 특별한 기능이 있다.
         * 외부 라이브러리의 종료 메서드가 주로 close 또는 shutdown 인데
         * destroyMethod 의 기본값이 (inferred) 으로 등록되어 있다.
         * 이 추론 기능은 close, shutdown 라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료메서드를 추론해서 호출해준다.
         * 추론 기능을 사용하기 싫으면 빈 공백을 지정하면 된다.
         */
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }

}
