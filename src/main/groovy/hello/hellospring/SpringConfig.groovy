package hello.hellospring

import hello.hellospring.repository.JdbcMemberRepository
import hello.hellospring.repository.MemberRepository
import hello.hellospring.repository.MemoryMemberRepository
import hello.hellospring.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.sql.DataSource

@Configuration
class SpringConfig {

    private dataSource

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource
    }

    @Bean
    MemberService memberService() {
        new MemberService(memberRepository())
    }

    @Bean
    MemberRepository memberRepository() {
//        new MemoryMemberRepository()
        new JdbcMemberRepository(dataSource)
    }
}
