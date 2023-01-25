package facades;

import dtos.DinnereventDTO;
import dtos.MemberDTO;
import entities.Dinnerevent;
import entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class MemberFacade {

    public MemberFacade() {
    }

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    public static MemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }



    public void deleteMember(int pn) {
        EntityManager em = emf.createEntityManager();
        Member a = (em.find(Member.class, (long) pn));
        try {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }





}
