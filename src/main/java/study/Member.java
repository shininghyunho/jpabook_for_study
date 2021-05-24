package study;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 엔티티 클래스임을 나타냄
@Table(name="Member") // 매핑
public class Member {
    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String username;

    // Column 을 표시하지 않으면 필드(age) 이름으로 매핑
    private Integer age;

    // Setter, Getter

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}