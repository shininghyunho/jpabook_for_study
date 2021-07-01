package study;

import study.entity.*;
import study.entity.item.Album;
import study.entity.item.Book;
import study.entity.item.Item;
import study.entity.item.Movie;

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
        member.setCreatedDate(new Date());
        member.setLastModifiedDate(new Date());
        em.persist(member);

        Delivery delivery = new Delivery();
        delivery.setCity("incheon");
        delivery.setStreet("yeonsu");
        delivery.setZipcode("bonga");
        delivery.setStatus(DeliveryStatus.COMP);
        //em.persist(delivery);

        Order order = new Order();
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(new Date());
        order.setMember(member); // member 관계매핑
        order.setDelivery(delivery); // delivery 관계매핑
        order.setCreatedDate(new Date());
        order.setLastModifiedDate(new Date());

        Album album = new Album();
        album.setName("album name");
        album.setPrice(100);
        album.setStockQuantity(10);
        album.setArtist("album artist");
        album.setEtc("etc...");
        em.persist(album);

        Book book = new Book();
        book.setName("book name");
        book.setPrice(101);
        book.setStockQuantity(11);
        book.setAuthor("book author");
        book.setIsbn("isbn...");
        em.persist(book);

        Movie movie = new Movie();
        movie.setName("movie name");
        movie.setPrice(102);
        movie.setStockQuantity(12);
        movie.setDirector("movie director");
        movie.setActor("movie actor");
        em.persist(movie);

        OrderItem orderItem = new OrderItem();
        orderItem.setCount(10);
        orderItem.setOrderPrice(5);
        orderItem.setOrder(order); // order 관계매핑
        orderItem.setItem(album); // item 관계매핑
        //em.persist(orderItem);
        em.persist(order); // delivery, orderItem 영속성 전이

        Category category = new Category();
        category.setName("all");
        category.addItem(album);
        category.addItem(book);
        category.addItem(movie);
        em.persist(category);

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
                    System.out.println(newItem.toString());
                    System.out.println(newItem.getCategories().get(0).toString());
                }
            }
        }
    }
}