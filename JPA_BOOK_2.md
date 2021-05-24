### Entity Table Annotaion

#### @Entity

​	해당 클래스를 테이블과 매핑한다고 JPA에게 알려줌.

​	@Entity가 사용된 클래스를 엔티티 클래스라고함.

#### @Table

​	엔티티 클래스와 매핑할 테이블 정보를 알려줌.

​	name 속성을 사용하여 물리적인 테이블과 연결 시킬수 있음.

​	ex) @Table(name="MEMBER")

#### @Id

​	엔티티 클래스의 필드를 테이블 기본키에 매핑.
​	
​	@Id가 사용된 필드를 식별자 필드라고함.

#### @Column

​	필드를 컬럼에 매핑. name 속성을 사용해 테이블 컬럼명에 매핑할 수 있음.

​	엔티티 클래스에서 @Column 표시를 안해준 필드는 필드명에 따른 컬럼명에 매핑됨.

## 엔티티 매니저, 엔티티 팩토리

Persistence 클래스가 persistence.xml을 참고하여
'jpabook'인 영속성 유닛(persistence-unit)을 찾아 엔티티 매니저 팩토리를 생성.

![](.\EntityManager.JPG)

### 엔티티 매니저 팩토리 생성

`EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");`

엔티티 매니저 팩토리는 JPA를 동작시키기 위한 기반 객체를 만들고
데이터베이스 커넥션 풀도 생성하는 비싼 작업이므로
애플리케이션에서 **단 한번만** 생성해주고 공유해서 사용.

### 엔티티 매니저 생성
`EntityManager em = emf.createEntityManager();`

앤티티 매니저는 JPA의 기능 대부분을 제공.
엔티티를 DB에  등록/수정/삭제/조회 할 수 있음.
앤티티 매니저는 내부에 데이터 소스(DB 커넥션)을 유지해
개발자는 앤티티 매니저를 하나의 가상 DB라고 생각할 수 있음.
(앤티티 매니저는 DB 커넥션과 밀접하므로 스레드간 공유 x)

### 트랜잭션
JPA를 사용하려면 항상 트랜잭션 안에서 데이터를 변경해야함.
트랜잭션을 시작하려면 엔티티 매니저에서 트랜잭션 API를 받아와야함.

트랜잭션 API를 받아옴

`EntityTransaction tx = em.getTransaction();`

정상동작하면 커밋하고 예외가 발생하면 롤백함.

## JPA를 활용한 등록/수정/삭제/조회

### 등록

자동으로 INSERT 쿼리를 생성해줌

`em.persist(member);`

### 수정
JPA는 어떤 엔티티가 변경되었는지 추척하는 기능이 있어
엔티티 값만 변경하면 알아서 UPDATE를 해준다.
`member.setAge(20);`

### 삭제
`em.remove(member);`

### 한 건 조회
엔티티 타입과 @Id로 테이블에서 조회할 수 있다.
`Member findMember = em.find(Member.class,id);`

### 여러건 조회
`TypedQuery<Member> query = 
em.createQuery("select m from Member m",Member.class);`
`List<Member> members = query.getResultList();`

앞선 조회와 달리 쿼리를 사용했다. 그 이유는 엔티티 객체를 대상으로
검색하려면 데이터베이스의 모든 데이터를 불러와 앤티티 객체로 변경하고
검색해야하는데 현실적이지 않으므로,
JPQL(Java Persistence Query Language)를 사용하여
쿼리를 통해 불러왔다. JPQL은 SQL 문법과 거의 유사한데, JPQL은 엔티티 객체를 대상으로 쿼리한다. 테이블이 아닌 클래스와 필드를 대상으로 한다. 반면 SQL은 데이터베이스 테이블을 대상으로 쿼리한다.

그래서 `em.createQuery("select m from Member m",Member.class);`에서 JPQL은 데이터 베이스 테이블을 전혀 알지 못한다. 이후에 JPA가 적절한 SQL 문을 만들어 주는것이다.