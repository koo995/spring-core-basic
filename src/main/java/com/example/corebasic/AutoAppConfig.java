package com.example.corebasic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔을 사용하려면 먼저 @Configuration 이 붙은 설정 정보를 사용한다.
 * 기존의 AppConfig 와는 다르게 @Bean 으로 등록한 클래스가 하나도 없다.
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig{

}
