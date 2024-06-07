package com.example.corebasic.member;

/**
 * 관례상 구현클래스가 하나만 있으면 뒤에서 Impl 을 붙여준다.
 */
public class MemberServiceImpl implements MemberService{

    /**
     * 현재... MemberServiceImpl 이 녀석은 MemberRepository 의 추상화와 구현체 모두에 의존하고 있기에 DIP 위반
     * 이제 이 구현체에 MemoryMemberRepository 에 관한 코드가 없다. -> 구현체에 의존하지 않는다. 추상화(인터페이스)에만 의존한다.
     */
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
