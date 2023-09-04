package hello.hellospring.domain;

public class Member {
    private Long id;
    // id는 고객이 정하는 id가 아니라 DB가 식별할 수 있는 값을 의미
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
