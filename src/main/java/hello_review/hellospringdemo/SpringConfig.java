package hello_review.hellospringdemo;

import hello_review.hellospringdemo.repository.MemberRepository;
import hello_review.hellospringdemo.repository.MemoryMemberRepository;
import hello_review.hellospringdemo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return  new MemoryMemberRepository();
    }

}
