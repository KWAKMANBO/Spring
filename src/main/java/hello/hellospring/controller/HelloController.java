package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController{

    @GetMapping("hello") // web에서 /hello라고 요청이 들어오면 hello를 호출해줌
    public String hello(Model model){
        model.addAttribute("data","spring!!");
        return "hello";
        // return값에 해당하는 hello html을 실행시킴
    }

    @GetMapping("hello-mvc")
    public String  helloMvc(@RequestParam(value = "name", required = false) String name, Model model ){
        // 외부에서 인자를 받아 올때는 어노테이션으로 @RequestParam을 이용함
        // 인자 name을 받아와서 String에 저장
        System.out.println(name);
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    // ResponseBody란?
    // http에서 Header부와 Body부가 나뉘는데
    // 이 Body부에 return문을 직접 넣어주는 기능을함
    public String helloString(@RequestParam("name") String name){
        // name에 spring을 입력하면
        // hello spring이 반환됨
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;



    }

}