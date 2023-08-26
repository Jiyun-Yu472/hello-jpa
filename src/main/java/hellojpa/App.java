package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

/**
 * Hello world!
 *
 */
public class App {
	@Transactional
    public static void main( String[] args ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx= em.getTransaction();
        tx.begin();
        
        try {
        	Member member = new Member();
        	//insert
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member); //PK 전략이 identity전략인 경우 바로 쿼리가 실행된다(쿼리를 실행시켜야 pk값을 알 수 있기 때문)
        	
            //select
//        	Member findMember = em.find(Member.class, 1L);
        	//JPQL(from의 대상의 데이블이아닌 객체를 칭한다.)
//        	List<Member> findMember = em.createQuery("select m from Member m", Member.class)
//        			.getResultList();
        	
        	//remove
//        	em.remove(1L);
        	
        	//update
//        	findMember.setName("HelloA_Mod"); //JPA 변경감지
            
        	//영속성과 1차캐시
        	member.setId(100L); //1차캐시에 저장
        	em.find(Member.class, 100L);// 1차캐시에서 데이터를 찾음
        	em.find(Member.class, 101L);// 1차캐시에서 데이터를 찾지 못하고  DB에서 조회
        	// 1차캐시에 존재하는 컬럼: id(PK), Entity, snapshot(해당 영속성 객체의 최초 select 상태)
        	
        	
        	
        	//영속성과 동일성 보장
//        	Member a = em.find(Member.class, 100L);
//        	Member b = em.find(Member.class, 100L);
//        	a == b; //한 트랜잭션에서 PK값이 같은 객체는 동일한 객체이다.
        	
            
            tx.commit(); //대부분의 쓰기작업은 commit 또는 rollback시 적용된다.(JPA flush() 호출)
            //flush호출시 하는일: 1차캐시의 각 entity와 snapshot비교, 변경감지후 update쿼리 생성, 트랜잭션동에서 생성된 sql문을 flush
            //flush호출하는 방법: em.flush(), commit(), JPQL 쿼리 실행시(sql 조회시전에 쓰기 작업이 DB와 동기화가 돼야하기 때문.)
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
        
        
        
        
        
        
        emf.close();
    }
}
 