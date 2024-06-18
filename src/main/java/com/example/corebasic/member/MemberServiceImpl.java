package com.example.corebasic.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 관례상 구현클래스가 하나만 있으면 뒤에서 Impl 을 붙여준다.
 */
@RequiredArgsConstructor
@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

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
