package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemberRepository
import hello.hellospring.repository.MemoryMemberRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

import static org.junit.jupiter.api.Assertions.assertThrows

// Spring까지 다 사용하는 통합테스트 - integration test!
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행! (DI사용가능)
@Transactional // test를 시작하기 전에 transaction을 걸고 테스트가 끝나면 rollback해버림 - setup, before_each가 필요 없음
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService
    @Autowired MemberRepository memberRepository

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
