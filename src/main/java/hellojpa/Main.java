package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Team userTeam = new Team();
            userTeam.setName("USERTEAM");
            em.persist(userTeam);

            Member member1 = new Member();
            member1.setName("wally");
            member1.setType(MemberType.USER);
            member1.setTeam(userTeam);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("jojo");
            member2.setType(MemberType.USER);
            member2.setTeam(userTeam);
            em.persist(member2);

            Team pointTeam = new Team();
            pointTeam.setName("POINTTEAM");
            em.persist(pointTeam);

            Member member3 = new Member();
            member3.setName("walker");
            member3.setType(MemberType.USER);
            member3.setTeam(pointTeam);
            em.persist(member3);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member1.getId());
            Team findTeam = findMember.getTeam();

            List<Member> members = findTeam.getMembers();

            for(Member m : members){
                System.out.println("member : "+m.getName());
            }
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }

}
