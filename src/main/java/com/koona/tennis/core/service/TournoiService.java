package com.koona.tennis.core.service;

import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.dto.TournoiDto;
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

    public TournoiDto getTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Tournoi tournoi = null;
        TournoiDto tournoiDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            
            tournoi = tournoiRepositoryImpl.readOne(id);
            
            tournoiDto = new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setCode(tournoi.getCode());
            tournoiDto.setNom(tournoi.getNom());
            
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

        return tournoiDto;
    }

    public void createTournoi(TournoiDto tournoiDto) {
        Session session = null;
        Transaction tx = null;
        
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            
            Tournoi tournoi = new Tournoi();
            tournoi.setCode(tournoiDto.getCode());
            tournoi.setNom(tournoiDto.getNom());
            tournoi.setId(tournoiDto.getId());
            
            tournoiRepositoryImpl.create(tournoi);
            
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
