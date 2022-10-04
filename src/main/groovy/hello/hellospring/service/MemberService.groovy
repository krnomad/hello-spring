package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemberRepository
import hello.hellospring.repository.MemoryMemberRepository

class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository()

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicatedMember(member)

        memberRepository.save(member)
        return member.id
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll()
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId)
    }

    private validateDuplicatedMember(Member member) {
        memberRepository.findById(member.id)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 입니다")
                })
    }
}