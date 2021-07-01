package study.entity;

import javax.persistence.*;

@Entity
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY) // 지연 로딩 추가
    private Order order;

    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    // Getter, Setter
    public Long getId(){return this.id;}

    public String getCity(){return this.city;}
    public void setCity(String city){this.city=city;}

    public String getStreet(){return this.street;}
    public void setStreet(String street){this.street=street;}

    public String getZipcode(){return this.zipcode;}
    public void setZipcode(String zipcode){this.zipcode=zipcode;}

    public DeliveryStatus getStatus(){return this.status;}
    public void setStatus(DeliveryStatus status){this.status=status;}

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", status=" + status +
                '}';
    }
}
