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
        String id="id1";
        Member newMember = new Member();
        newMember.setId(id);
        newMember.setUsername("최현호");
        newMember.setAge(26);

        Member newMember2 = new Member();
        newMember2.setId("id2");
        newMember2.setUsername("이재훈");
        newMember2.setAge(26);

        // 등록
        em.persist(newMember);
        em.persist(newMember2);

        // 수정 - 영속성 때문에 자동으로 UPDATE 쿼리를 날려줌
        newMember.setAge(20);

        // 한건 조회
        System.out.println("# 한건 조회");
        Member member = em.find(Member.class,id);
        System.out.println("id : "+member.getId()+
                ", name : "+member.getUsername()+", age : "+member.getAge());

        // 여러건 조회(목록 조회)
        System.out.println("\n# 여러건 조회");
        List<Member> members = em.createQuery("select m from Member m",Member.class).getResultList();
        for (Member person : members){
            System.out.println("id : "+person.getId()+
                    ", name : "+person.getUsername()+", age : "+person.getAge());
        }

        // 삭제
        em.remove(newMember);
        em.remove(newMember2);
    }
}
