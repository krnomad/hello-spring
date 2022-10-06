package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
// 정형화된 로직
// Contoller를 통해서 외부 요청을 받고
// Service에서는 비지니로직을 처리하고
// Repository에서 데이터를 저장한다
class MemberService {

    private final MemberRepository memberRepository

    MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository
    }
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
        memberRepository.findByName(member.name)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 입니다")
                })
    }
}
