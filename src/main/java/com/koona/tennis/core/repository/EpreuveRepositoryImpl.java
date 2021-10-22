package com.koona.tennis.core.repository;

import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.entity.Epreuve;
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

}
