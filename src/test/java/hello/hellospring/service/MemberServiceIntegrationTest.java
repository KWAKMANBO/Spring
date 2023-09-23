package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// Spring 테스트 할때 사용하는 어노테이션
@Transactional
class MemberServiceIntegrationTest {

    // MmeberService와 MemoryMemberRepository 두개를 사용할 필요 x
    // 같은걸 쓰는게 좋음
    // new로 서로다른 객체가 생성하게되면 다른 객체기 떄문에 내용물이 달라질 수 있음
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository = new MemoryMemberRepository();
    // TestCase에서는 Test할때만 사용하므로


    @Test
        // Test는 메서드명을 한글로 바꿔도됨
   // @Commit // @Commit 어노테이션을 사용하면 실제 DB에 반영/
    void 회원가입() {

        // given - 무언가가 주어짐
        Member member = new Member();
        member.setName("spring");

        // when - 테스트를 실행 했을때
        Long saveId = memberService.join(member);

        // then - 결과가 이게 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //test는 정상 플로우도 중요한데 예외 플로우도 중요함

    @Test
    public void 중복_회원_예의(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //성공한버전
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}