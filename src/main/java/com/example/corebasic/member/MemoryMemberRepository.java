package com.example.corebasic.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    /**
     * 사실은 동시성 이유가 있을 수 있기 때문에 ConcurrentHashMap 을 사용하는 것이 좋다.
     * 하지만 여기서는 간단하게 HashMap 을 사용한다. 예제니까
     */
    private static final Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
