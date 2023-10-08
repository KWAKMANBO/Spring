package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // interface가 interface를 상속받을때는 extedns를 사용함
    // java에서 인터페이스는 다중상속이 가능함
    //<T, type> 객체, type은 table에서 Pk의 타입


    // JPQL : select m from member where m.name = ?
    @Override
    Optional<Member> findByName(String name);
    // 구현할 필요 x
    // springDataJPA가 자동으로 구현체를 만들어ㅒ


}
