package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main2 {

    public static void main(String[] args){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            try{
                Member memberA = new Member();
                memberA.setName("wally");
                em.persist(memberA);//영속성 컨텍스트에 저장
                Member memberB = em.find(Member.class, memberA.getId());

                System.out.println("memberA : "+memberA.hashCode());
                System.out.println("memberA : "+memberB.hashCode());

                tx.commit();

            }catch (Exception e){
                tx.rollback();
            }finally {
                em.close();
            }
            emf.close();

        }

}

