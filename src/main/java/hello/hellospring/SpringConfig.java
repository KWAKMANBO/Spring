package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

   private DataSource dataSource;
   // applicationproperties파일을 보고 자동으로 datasource를 생성해줌


   @Autowired
   public SpringConfig(DataSource dataSource){
       this.dataSource =dataSource;
   }

     // Spring Bean을 등록한다는 의미의 어노테이션
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
        // 아래에서 생성 한 MemoryMemberRepository 객체를 전달받음
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        // 생성한 MemoryMemberRepository 객체를 MemberSerivce객체가 생성될대 전달해주어야함
        return new JdbcMemberRepository(dataSource);
    }

}
