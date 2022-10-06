package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.springframework.stereotype.Repository

class MemoryMemberRepository implements MemberRepository {

    // don't consider concurrency issue
    private static Map<Long, Member> store = [:] // id, member
    private static long sequence = 0L

    @Override
    Member save(Member member) {
        member.setId(++sequence)
        store.put(member.id, member)
        return member
    }

    @Override
    Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id))
    }

    @Override
    Optional<Member> findByName(String name) {
        return store.values().stream()
            .filter { it.name == name }
            .findAny()
    }

    @Override
    List<Member> findAll() {
        return new ArrayList<Member>(store.values())
    }

    @Override
    void clearStore() {
        store.clear()
    }
}
