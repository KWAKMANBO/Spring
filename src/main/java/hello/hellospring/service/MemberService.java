package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// 실행되면 spring이 자동으로 Service에 등록을 해줌
// 나중에 Autowired를 통해서 객체가 사용하기위해서 필요한 어노테이션임

@Transactional
//@Service
public class MemberService {


    // 아래 방식을 사용하면 MemberServiceTest에서 사용하는 MemoryMemberRepository가 서로 다른 객체를 사용함
    // 이과정에서 문제가 발생할 수 있음
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 아래 같은 방식을 사용해서 같은 객체를 사용하도록함
    // 이 방식을 Defendecies Ingections라고함
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return 
     */
    public Long join(Member member){
        // memeber를 저장하고 그 member의 아이디를 반환
        
        // 같은 이름이 있는 중복회원은 회원가입안됨

        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{ // null이 아닌 다른값이면 즉 값이 존재한다면 
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });// result 값이 존재한다면
        */
        validateDuplicateMember(member);
        // member의 name이 존재한다면 이미 존재한느 회원입니다를 출력

        memberRepository.save(member);
        return member.getId();
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
      return  memberRepository.findAll();
      // findAll의 반환타입은 리스트
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findbyId(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent( m->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }


}
