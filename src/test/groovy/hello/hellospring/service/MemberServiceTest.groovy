package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemoryMemberRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*

// 스프링 없이 unit test - 단위 테스트가 나오는 것이 좋은 테스트 구조이다.
class MemberServiceTest {

    MemberService memberService
    MemoryMemberRepository memberRepository

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepository()
        // DI
        memberService = new MemberService(memberRepository)
    }

    @AfterEach
    void tearDown() {
        memberRepository.clearStore()
    }

    @Test
    void '회원가입'() {
        // given
        Member member = new Member()
        member.setName('hello')

        // when
        Long saveId = memberService.join(member)

        // then
        Member foundMember = memberService.findOne(saveId).get()
        member.name == foundMember.name
    }

    @Test
    void '중복 회원 예외'() {
        // given
        Member member1 = new Member()
        member1.setName('spring')

        Member member2 = new Member()
        member2.setName('spring')

        // when
        memberService.join(member1)

        def e = assertThrows(IllegalStateException.class) {
            memberService.join(member2) // exception expected
        }

        assert e.getMessage() == "이미 존재하는 회원 입니다"

        // then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}