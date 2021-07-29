package study.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    private String name;

    // embedded type
    @Embedded
    private Address address;

    // 관계 inverse
    @OneToMany(mappedBy = "member") // 주인이 정한 필드명
    private List<Order> orders = new ArrayList<Order>();

    // Getter, Setter
    public Long getId(){return this.id;}
    //public void setId(Long id){this.id=id;}

    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}

    // orders getter 주인이 아니기에 setter 는 없음
    public List<Order> getOrders(){
        return this.orders;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
