package study;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            // do_something
            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback(); // 실패시 롤백
        } finally {
            em.close(); // 엔티티 매니저 종료
        }
        emf.close(); // 엔티티 매니저 팩토리 종료
    }
}