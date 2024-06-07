package com.example.corebasic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔을 사용하려면 먼저 @Configuration 이 붙은 설정 정보를 사용한다.
 * 기존의 AppConfig 와는 다르게 @Bean 으로 등록한 클래스가 하나도 없다.
 * 탐색할 베이스 패키지를 설정할 수 있다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
 * 그렇다면 디폴트는 무엇이냐? 바로 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
 * 여기서는 package com.example.corebasic
 * 왠만하면 디폴트를 따르는 관례를 지키는 것이 좋다.
 */
@Configuration
@ComponentScan(
        basePackages = "com.example.corebasic",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig{

}
