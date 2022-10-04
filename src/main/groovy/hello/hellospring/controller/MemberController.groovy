package hello.hellospring.controller

import hello.hellospring.domain.Member
import hello.hellospring.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class MemberController {
    private final MemberService memberService

    @Autowired // spring container 있는 memberService 생성자를 그대로 가져다가 사용한다
    MemberController(MemberService memberService) {
        this.memberService = memberService
    }
}
