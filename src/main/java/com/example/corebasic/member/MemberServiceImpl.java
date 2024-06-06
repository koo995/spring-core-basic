package com.example.corebasic.member;

/**
 * 관례상 구현클래스가 하나만 있으면 뒤에서 Impl 을 붙여준다.
 */
public class MemberServiceImpl implements MemberService{

    /**
     * 현재... MemberServiceImpl 이 녀석은 MemberRepository 의 추상화와 구현체 모두에 의존하고 있기에 DIP 위반
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
