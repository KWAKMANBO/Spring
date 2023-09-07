package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member); // 회원정보 저장
    Optional<Member> findbyId(Long id); // Id을 통해서찾기
    Optional<Member> findByName(String name); // 이름을 통해서 찾기
    List<Member> findAll(); // 모든 회원정보를 찾기
    // Optional이란? 반환값이 null일때 예외 처리를 해줌
}
