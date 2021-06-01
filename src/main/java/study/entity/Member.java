package study.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    private String name;

    private String city;
    private String street;
    private String zipcode;

    // 관계 inverse
    @OneToMany(mappedBy = "member") // 주인이 정한 필드명
    private List<Order> orders = new ArrayList<Order>();

    // Getter, Setter
    public Long getId(){return this.id;}
    //public void setId(Long id){this.id=id;}

    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}

    public String getCity(){return this.city;}
    public void setCity(String city){this.city=city;}

    public String getStreet(){return this.street;}
    public void setStreet(String street){this.street=street;}

    public String getZipcode(){return this.zipcode;}
    public void setZipcode(String zipcode){this.zipcode=zipcode;}

    // orders getter 주인이 아니기에 setter 는 없음
    public List<Order> getOrders(){
        return this.orders;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
