package study;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args){
        // 엔티티 매니저 팩토리
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook"); // 영속성 엔티티
        // 엔티티 매니저
        EntityManager em = emf.createEntityManager();
        // 트랜잭션
        EntityTransaction tx = em.getTransaction();
        /* 코드 없음 */
        em.close();
        emf.close(); // 엔티티 매니저 팩토리 종료
    }
}
