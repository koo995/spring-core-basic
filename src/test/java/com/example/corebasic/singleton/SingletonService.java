package com.example.corebasic.singleton;

/**
 * 참고로 싱글톤 패턴을 구현하는 방법은 매우 많다.
 * 그러나 수많은 문제가 있다.
 * 1. 수 많은 구현을 위한 코드
 * 2. 의존관계상 클라이언트가 구체클래스에 의존해야한다. DIP 위반하게 된다.( 구체클래스.getInstance() )
 * 3. 클라이언트가 구체클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
 * 4. 내가 지정해서 가져오기 때문에 유연하게 테스트하기 어렵다
 * 5. private 생성자로 자식 클래스를 만들기 어렵다.
 * 등등... 그래서 안티패턴으로 불리기도 한다.
 * 그런데 스프링프레임워크는 이러한 싱글톤 패턴의 단점을 모두 제거하고 객체를 싱글톤으로 유지한다.
 */
public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
     * 정말 중요하다. 이것이 싱글톤 패턴이다.
     * 컴파일 오류로 외부에서 객체 생성을 막아준다.
     */
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
