package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// JPA에서 사용하기위해서는 @entity 어노테이션을 사용해서 mapping을 해야@
// jpa가 관리하는 Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // pk임을 명시
    // DB가 알아서 id값을 생성해주는것을 IDENTITY라고함
    private Long id;
    // id는 고객이 정하는 id가 아니라 DB가 식별할 수 있는 값을 의미

    // 만약 변수명 DB의 column명이 다르다면
    // @Column(name = "username") 형식으로 매핑해줄 수 있음
    private String name;


    public void setId(Long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
