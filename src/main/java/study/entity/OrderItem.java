package study.entity;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    // 관계 inverse
    @ManyToOne
    @JoinColumn(name = "ITEM_ID") // 외래키 이름
    private Item item; // item

    // 관계 inverse
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order; // order

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    // getter setter
    public Long getId() {
        return id;
    }

    public int getOrderPrice() {
        return orderPrice;
    }
    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    // item getter setter
    public Item getItem(){return this.item;}
    // item -> orderItem 인 경우가 거의 없어서 단방향 설정
    public void setItem(Item item){
        this.item=item; // owner
    }

    // order getter setter
    public Order getOrder(){return this.order;}

    public void setOrder(Order order){
        // 기존 inverse 에서 내 관계 끊기
        if (this.order != null){
            this.order.orderItems.remove(this);
        }

        // 새로운 관계
        this.order=order; // owner
        order.getOrderItems().add(this); // inverse
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderPrice=" + orderPrice +
                ", count=" + count +
                '}';
    }
}
