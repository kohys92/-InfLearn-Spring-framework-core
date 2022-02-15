package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    새로운 할인 정책 적용시 간단하게 new FixDiscountPolicy() 에서 new RateDiscountPolicy() 를 선언해주면 된다고 생각했지만,
    OCP, DIP 를 위반하는 것이다. 왜냐하면 비록 객체지향적이게 잘 설계한 덕분에 조그만 변경하면 된다하지만 OrderServiceImpl 이 어쨌거나
    구체화인 FixDiscountPolicy 와 RateDiscountPolicy에 의존하고 있기 때문이다. 즉, DicountPolicy 인터페이스에만 의존해야되는데
    어떤 도구가 알아서 바꿔주지 않으면 DIP 를 지킬 수가 없다. 그래서 어떤 도구가 필요한지 알아보자.
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /*
    이렇게 DicountPolicy 만 선언해준다면 NullPointerException 이 발생하게된다. discountPolicy 는 빈 객체이기 때문이다.
     */
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
