package hello_review.hellospringdemo.domain;

public class Member {
    private Long id;
    private String name;

    public Long getId(){
        // Id를 반환하는 메서드
        return id;
    }

    public void setId(Long id){
        // Id를 설정하는 메서드
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        // 이름을 설정하는 메서드
        this.name = name;
    }

}
