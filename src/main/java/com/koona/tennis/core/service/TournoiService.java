package com.koona.tennis.core.service;

import com.koona.tennis.core.EntityManagerHolder;
import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.dto.TournoiDto;
import com.koona.tennis.core.entity.Tournoi;
import com.koona.tennis.core.repository.TournoiRepositoryImpl;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        //Session session = null;
        EntityManager em = null;
//        Transaction tx = null;
        EntityTransaction tx = null;
        Tournoi tournoi = null;
        TournoiDto tournoiDto = null;

        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
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
            if (em != null) {
                em.close();
            }
        }

        return tournoiDto;
    }

    public void createTournoi(TournoiDto tournoiDto) {
//        Session session = null;
//        Transaction tx = null;

        EntityManager em = null;
        EntityTransaction tx = null;
        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();

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
            if (em != null) {
                em.close();
            }
        }
    }

    public void deleteTournoi(Long id) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tournoiRepositoryImpl.delete(id);
            tx.commit();

        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
