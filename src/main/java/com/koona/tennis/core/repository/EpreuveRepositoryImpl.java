package com.koona.tennis.core.repository;

import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.entity.Epreuve;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Steve KOUNA
 */
public class EpreuveRepositoryImpl {

    public Epreuve readOne(Long id) {
        Epreuve epreuve = new Epreuve();

        Session session = null;

        session = HibernateUtil.getSessionFactory().openSession();
        epreuve = session.get(Epreuve.class, id);
        System.out.println("Epreuve lue");

        return epreuve;
    }

    
    public List<Epreuve> readAll(String code) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Epreuve> query =  session.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);
        query.setParameter(0, code);
        List<Epreuve> epreuves = query.getResultList();
        
        System.out.println("Epreuve lues");
        
        return epreuves;
    }

}
