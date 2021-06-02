package study.entity;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    // 관계 owner
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") // 외래키 이름
    private Member member;

    // 관계 inverse
    @OneToMany(mappedBy = "order") // 주인이 정한 필드명
    List<OrderItem> orderItems = new ArrayList<OrderItem>();

    // delivery
    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // getter setter
    public Long getId(){return this.id;}
    //public void setId(Long id){this.id=id;}

    public Date getOrderDate(){return this.orderDate;}
    public void setOrderDate(Date orderDate){this.orderDate=orderDate;}

    public OrderStatus getStatus(){return this.status;}
    public void setStatus(OrderStatus status){this.status=status;}

    // member setter getter
    public Member getMember(){return this.member;}

    public void setMember(Member member){
        // 기존 관계 제거
        // (inverse 관계 제거, 주인만 변경되니 수동으로 없애줘야함)
        if (this.member != null){
            member.getOrders().remove(this);
        }

        // 새로운 연관관계
        this.member=member; // 주인 연결
        member.getOrders().add(this); // inverse 연결
    }

    // orderItem getter
    public List<OrderItem> getOrderItems(){return this.orderItems;}

    // delivery
    public Delivery getDelivery() { return delivery; }
    public void setDelivery(Delivery delivery) {
        // inverse 에서 주인 제거
        if (this.delivery != null){
            delivery.setOrder(null);
        }
        
        // 새로운 연관관계
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status=" + status +
                '}';
    }
}
