package study.entity;

import javax.persistence.*;

@Entity
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY) // 지연 로딩 추가
    private Order order;

    // embedded type
    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    // Getter, Setter
    public Long getId(){return this.id;}

    public DeliveryStatus getStatus(){return this.status;}
    public void setStatus(DeliveryStatus status){this.status=status;}

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
