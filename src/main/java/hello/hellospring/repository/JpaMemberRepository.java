package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    private final EntityManager em;
    // jpa는 EntityManager을 이용해서 동작이됨
    // dependecies에 추가한 data-jpa가 있으므로 스프링이 자동으로 EntityMananger을 자동으로 생성해줌


    @Override
    public Member save(Member member) {
         em.persist(member);
         // jpa가 자동으로 쿼리문을 짜서 DB에 저장해줌
         // persits 영속하다, 영구저장하다
         return member;
    }

    @Override
    public Optional<Member> findbyId(Long id) {
        Member member = em.find(Member.class, id);
        // table에서 pk는 위와 같이 쉽게 조회할 수 있음
                return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
      List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
               .setParameter("name",name)
               .getResultList();

      return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        //pk가 아닌 column같은 경우에는 jpql 객체지향 쿼리언어를 사용해야함
        // Member m = m as Member
        // select m member 객체 자체를 selece하는 방식
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // commnat + option + n 을 누르면 inline variable로 만들어줌
        // 변수 할당 후 리턴 형식을 리턴에 변수 할당하는 방식으로 chagne해줌
    }
}
