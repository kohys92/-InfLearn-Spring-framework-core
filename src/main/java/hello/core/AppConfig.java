package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
Constructor Injection: To obey the Dependency Inversion Principle, we need an AppConfig class that manages
instances of subclasses to prevent Client from associating with the instantiated classes (e.g. MemoryMemberRepo(),
FixDiscountPolicy()).

 의존관계 주입/의존성 주입 : 관심사를 분리하다. Dependency Inversion Principle 을 지키기 위해선 관심사를 분리해야하나다. 기존의 MemberServiceImpl 은
 인터페이스 뿐만 아니라 하위의 클래스 인스탠스까지 연결되어 있었다. AppConfig 를 관리자의 역활로 만들어서 구현 객체를 AppConfig 안에 생생해주어
 클라이언트와 구체 객체의 연결을 끊어준다. 더 이상 MemberServiceImpl 또는 OrderServiceImpl 은 구체화 객체인 MemoryMemberRepo,
 FixDiscountRepo 와 연결될 일이 없다.
 **/

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
