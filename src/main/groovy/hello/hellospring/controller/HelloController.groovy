package hello.hellospring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController {
    // 동적 컨텐츠 방식
    @GetMapping("hello")
    String hell(Model model) {
        model.addAttribute("data", "hello!!")
        return "hello" // template name (view resolve will find resources:templates/${viewName
    }

    @GetMapping("hello-mvc")
    //String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
    String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name)
        return 'hello-template'
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello $name"
    }

    // API방식: 객체를 반환하면서 ResponseBody를 붙이면 Spring은 json을 반환한다
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello()
        hello.setName(name)
        return hello
    }

    static class Hello {
        String name
    }
}
