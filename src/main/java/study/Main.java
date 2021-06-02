package study;

import study.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * chapter6 다대일 일대일 다대다 연관관계 매핑
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

        Delivery delivery = new Delivery();
        delivery.setCity("incheon");
        delivery.setStreet("yeonsu");
        delivery.setZipcode("bonga");
        delivery.setStatus(DeliveryStatus.COMP);
        em.persist(delivery);

        Order order = new Order();
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(new Date());
        order.setMember(member); // member 관계매핑
        order.setDelivery(delivery); // delivery 관계매핑
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

        Category category = new Category();
        category.setName("food");
        em.persist(category);

        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setItem(item);
        itemCategory.setCategory(category);
        em.persist(itemCategory);

        // print
        List<Member> members = em.createQuery("select m from Member m",Member.class).getResultList();
        for (Member newMember:members){
            List<Order> orders = newMember.getOrders();
            System.out.println(newMember.toString());
            for(Order newOrder:orders){
                System.out.println(newOrder.toString());
                System.out.println(newOrder.getDelivery().toString());
                List<OrderItem> orderItems = newOrder.getOrderItems();
                for(OrderItem newOrderItem:orderItems){
                    System.out.println(newOrderItem.toString());
                    Item newItem = newOrderItem.getItem();
                    List<ItemCategory> itemCategories = item.getItemCategories();
                    for (ItemCategory newItemCategory : itemCategories){
                        System.out.println(newItemCategory.toString());
                        System.out.println(newItemCategory.getCategory().toString());
                    }
                }
            }
        }
    }
}