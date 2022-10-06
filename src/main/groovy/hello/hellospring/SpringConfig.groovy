package hello.hellospring

import hello.hellospring.repository.MemberRepository
import hello.hellospring.repository.MemoryMemberRepository
import hello.hellospring.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {
    @Bean
    MemberService memberService() {
        new MemberService(memberRepository())
    }

    @Bean
    MemberRepository memberRepository() {
        new MemoryMemberRepository()
    }
}
