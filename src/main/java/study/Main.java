package study;

import study.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * 데이터 중심 설계의 잘못된 엔티티 예제
 */
public class Main {
    public static void main(String[] args){
        // 엔티티 매니저 팩토리
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook"); // 영속성 엔티티
        // 엔티티 매니저
        EntityManager em = emf.createEntityManager();
        // 트랜잭션
        EntityTransaction tx = em.getTransaction();

        // 스레드 단위 실행
        try{
            tx.begin(); // 트랜잭션 시작
            do_something(em);
            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback(); // 실패시 롤백
        } finally {
            em.close(); // 엔티티 매니저 종료
        }
        emf.close(); // 엔티티 매니저 팩토리 종료
    }

    public static void do_something(EntityManager em){
        Member member = new Member();
        member.setName("shininghyunho");
        member.setCity("seoul");
        member.setStreet("dongdaemooon");
        member.setZipcode("zachibang");
        em.persist(member);

        Order order = new Order();
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(new Date());
        order.setMember(member); // member 관계매핑
        em.persist(order);

        Item item = new Item();
        item.setName("protein");
        item.setPrice(100);
        item.setStockQuantity(100);
        em.persist(item);

        OrderItem orderItem = new OrderItem();
        orderItem.setCount(10);
        orderItem.setOrderPrice(5);
        orderItem.setOrder(order); // order 관계매핑
        orderItem.setItem(item); // item 관계매핑
        em.persist(orderItem);

        // print
        List<Member> members = em.createQuery("select m from Member m",Member.class).getResultList();
        for (Member newMember:members){
            //System.out.println("맴버 이름 : "+newMember.getName());
            List<Order> orders = newMember.getOrders();
            System.out.println(newMember.toString());
            for(Order newOrder:orders){
                //System.out.print("주문 날짜 : ");
                //System.out.println(newOrder.getOrderDate());
                System.out.println(newOrder.toString());
                List<OrderItem> orderItems = newOrder.getOrderItems();
                for(OrderItem newOrderItem:orderItems){
                    System.out.println(newOrderItem.toString());
                    System.out.println(newOrderItem.getItem().toString());
                    //System.out.println("주문 갯수 : "+newOrderItem.getCount());
                    //System.out.println("상품 이름 : "+newOrderItem.getItem().getName());
                }
            }
        }
    }
}