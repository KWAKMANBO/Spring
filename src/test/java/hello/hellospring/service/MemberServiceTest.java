package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // MmeberService와 MemoryMemberRepository 두개를 사용할 필요 x
    // 같은걸 쓰는게 좋음
    // new로 서로다른 객체가 생성하게되면 다른 객체기 떄문에 내용물이 달라질 수 있음
    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        // 각 테스트를 실행하기전에 beforeEach 코드를 수행함
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    // AfterEach는 Test가 끝날때마다 실행되게 해주는 어노테이션
    public void afterEach(){
        // 테스트끝날때마다 실행되서 repository를 비워줌
        // 테스트는 서로 의존관계없이 실행되도록 설계가 되어야함
        memberRepository.clearStore();
    }

    @Test
        // Test는 메서드명을 한글로 바꿔도됨
    void 회원가입() {

        // given - 무언가가 주어짐
        Member member = new Member();
        member.setName("hello");

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

        // 실패한 버전
        // win ctrl + alt + v로 손쉽게 변수로 변형 가능 
        // mac은 cmd + alt + 
       
       // NullPointerException e = assertThrows(NullPointerException.class, () -> memberService.join(member2));

        // 정석적인 version
        /*   try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            // 성공버전
            //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

            // 실패버전
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
        }*/


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}