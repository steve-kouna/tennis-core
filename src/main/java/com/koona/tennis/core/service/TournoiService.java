package com.koona.tennis.core.service;

import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.entity.Tournoi;
import com.koona.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author Steve KOUNA
 */
public class TournoiService {
    
    private TournoiRepositoryImpl tournoiRepositoryImpl;


    public TournoiService() {
        this.tournoiRepositoryImpl = new TournoiRepositoryImpl();
    }
    
    public Tournoi getTournoi(Long id){
        Tournoi tournoi = tournoiRepositoryImpl.readOne(id);
        return tournoi;
    }
    
    public void createTournoi(Tournoi tournoi) {
        tournoiRepositoryImpl.create(tournoi);
    }
    
    public void deleteTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepositoryImpl.delete(id);
            tx.commit();

        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
