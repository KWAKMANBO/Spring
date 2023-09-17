package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){

        Member member = new Member();
        member.setName(form.getName());
        // member의 Name를 form에서 가지고온 Name 설정

        System.out.println("member = " + member.getName());

        memberService.join(member);
        // member를 회원가입 시킴

        return "redirect:/";
        // 모든 과정이 끝나면
        // 즉 회원가입이 완료되면 홈 화면으로 돌아감
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        System.out.println(members);
        return "members/memberList";
        // model 은 Controller에서 생성한 데이터를 담아서 View로 전달할 때 사용하는 객체.
        // addAttribute("key", "value")형식으로 사용하여 전달할 데이터를 세팅함
        // addAttribute(String name, Object value)
        // value 객체를 name이름으로 추가함
        // name으로 지정한 값을 이용해서 지정한 값을 사용할 수 있음
    }



}
