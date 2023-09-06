package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

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
