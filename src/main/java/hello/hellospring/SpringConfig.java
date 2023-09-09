package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
     // Spring Bean을 등록한다는 의미의 어노테이션
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
        // 아래에서 생성 한 MemoryMemberRepository 객체를 전달받음
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        // 생성한 MemoryMemberRepository 객체를 MemberSerivce객체가 생성될대 전달해주어야함
    }

}
