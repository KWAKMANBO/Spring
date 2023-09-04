package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository =  new MemoryMemberRepository();

    // 테스트 과정에서 앞에서 저장한 객체들이 남아있어서 오류가 일어날 수 있으므로
    // 테스트가 끝날때마다 리포지토리를 지워주는 코드를 작성해주어야함
    @AfterEach
    // AfterEach는 Test가 끝날때마다 실행되게 해주는 어노테이션
    public void afterEach(){
        // 테스트끝날때마다 실행되서 repository를 비워줌
        // 테스트는 서로 의존관계없이 실행되도록 설계가 되어야함
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findbyId(member.getId()).get();

        //System.out.println("result = " + (result == member));
        // 모든 결과를 글자로 다볼 수는 없음

        //Assertions.assertEquals(member, result);
        // result와 member가 같은 값인지 확인해줌

        //Assertions.assertThat(member).isEqualTo(result);
        // member가 result와 똑같은지 확인

        // static 추가해서 Assertion은 생략하기 가능
        assertThat(member).isEqualTo(result);
        // member가 result와 똑같은지 확인
    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

       List<Member> result =  repository.findAll();

       assertThat(result.size()).isEqualTo(2);
    }

}
