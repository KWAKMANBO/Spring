package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.lang.reflect.AnnotatedArrayType;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 실무에서는 동시성 문제를 고려해야 하지만
    // 간단한 실습예제 이기 때문에 고려x
    private static Map<Long, Member > store = new HashMap<>();
    // map이란? 키 밸류 쌍으로 구분되는 자료구조형
    // 순서 중요 x, 값은 중복가능 but key는 고유해야함
    private static long sequence = 0L;
    // key값을 생성해줌



    // MemberRepository를 구현한다는 의미
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // sequence를 1증가시키고 값을 member의 id로 set
        store.put(member.getId(), member);// map형 자료에 값을 삽입, put메서드를 이용
        return member;
    }

    @Override
    public Optional<Member> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
        // null이 반환될수도 있으므로 optional을 이용해서
        // null이 반환되도 이용할 수 있도록함
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member ->member.getName().equals(name))
        .findAny();
        // return stort.values().stream().filter(member ->{
        // return member.getName().equals(name);
        // }).findAny()
        // 와 동일한 코드

        // member의 이름을 가져와 입력된 문자열 name과 같은것이 있는지 확인함
        // 잇으면 바로 종료 없으면 끝까지 loop
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store에 있는 value를 ArrayList로 저장해서 반환해줌
    }

    public void clearStore(){
        store.clear();
    }
}

// 동작하는지 확인하는 방법
// 테스트 케이스를 작성하기
//
