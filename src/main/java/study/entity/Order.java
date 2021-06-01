package study.entity;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    // member fk
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // getter setter
    public Long getId(){return this.id;}
    public void setId(Long id){this.id=id;}

    public Long getMemberId(){return this.memberId;}
    public void setMemberId(Long memberId){this.memberId=memberId;}

    public Date getOrderDate(){return this.orderDate;}
    public void setOrderDate(Date orderDate){this.orderDate=orderDate;}

    public OrderStatus getStatus(){return this.status;}
    public void setStatus(OrderStatus status){this.status=status;}
}
