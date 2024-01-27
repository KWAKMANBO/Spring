package hello_review.hellospringdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 모두 같은 폴더에 있어야 제대로 실행됨
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        // @RequestParam("name") : name이라는 파라미터를 받아와서 helloMvc 메소드의 파라미터 name에 넣어줌
        // name은 쿼리파라미터 not http body
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        // @ResponseBody : http의 body에 이 데이터를 직접 넣어주겠다는 의미
        // http의 body에 이 데이터를 직접 넣어주겠다는 의미
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // 객체 생성
        hello.setName(name);
        return hello; // 객체 반환 (json 형태로 반환
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
