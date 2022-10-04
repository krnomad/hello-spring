package hello.hellospring.repository

import hello.hellospring.domain.Member

interface MemberRepository {
    Member save(Member member)
    Optional<Member> findById(Long id)
    Optional<Member> findByName(String name)
    List<Member> findAll()

    void clearStore()
}