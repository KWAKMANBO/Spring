package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class MemberController {
    // 아무것도 작성 x --> spring이 실행될때 @Controller 어노테이션이 있다면 spring이 자동으로 MemberController 객체를 생성해
    // 컨테이너에 넣어둠 이거를 spring bin이라고함

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        // Autowired란?
        // 객체 생성시 호출 하는 생성자에 Autowired 어노테이션이 있다면
        // 스프링 컨테이너에서 해당 객체를 가지고와줌
        this.memberService = memberService;
    }

    // MemberController은 MemberService를 필요로함
    // new MemberService로 객채 생성해 가져다 쓸 수 있음

    // spring에 등록이되면 spring container에게서 받아서
    // 쓰도록 바꾸어야함

    // ??why
    // new에서 하면 다른 컨트롤러에서 MemberService에서 사용하면 새로 생성하는것은 낭비
    // 하나 생성해서 그거를 받아다 쓰는게 더 효율적




}
