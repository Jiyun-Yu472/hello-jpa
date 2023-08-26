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
        	
            //select
//        	Member findMember = em.find(Member.class, 1L);
        	//JPQL(from의 대상의 데이블이아닌 객체를 칭한다.)
//        	List<Member> findMember = em.createQuery("select m from Member m", Member.class)
//        			.getResultList();
        	
        	//remove
//        	em.remove(1L);
        	
        	//update
//        	findMember.setName("HelloA_Mod"); //JPA 변경감지
        	
        	
            em.persist(member);
            
            tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
        
        
        
        
        
        
        emf.close();
    }
}
 