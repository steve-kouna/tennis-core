package com.koona.tennis.core.service;

import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.dto.EpreuveFullDto;
import com.koona.tennis.core.dto.EpreuveLightDto;
import com.koona.tennis.core.dto.TournoiDto;
import com.koona.tennis.core.entity.Epreuve;
import com.koona.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Steve KOUNA
 */
public class EpreuveService {

    private EpreuveRepositoryImpl epreuveRepositoryImpl;

    public EpreuveService() {
        this.epreuveRepositoryImpl = new EpreuveRepositoryImpl();
    }

    public Epreuve getEpreuve(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepositoryImpl.readOne(id);
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
        return epreuve;
    }

    
    public EpreuveFullDto getEpreuveAvecTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveFullDto epreuveDto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepositoryImpl.readOne(id);
            Hibernate.initialize(epreuve.getTournoi());
            tx.commit();
            
            epreuveDto = new EpreuveFullDto();
            epreuveDto.setId(epreuve.getId());
            epreuveDto.setAnnee(epreuve.getAnnee());
            epreuveDto.setTypeEpreuve(epreuve.getTypeEpreuve());
            
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            
            epreuveDto.setTournoi(tournoiDto);

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
        return epreuveDto;
    }

    
    public EpreuveLightDto getEpreuveSansTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveLightDto epreuveDto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepositoryImpl.readOne(id);
            tx.commit();
            
            epreuveDto = new EpreuveLightDto();
            epreuveDto.setId(epreuve.getId());
            epreuveDto.setAnnee(epreuve.getAnnee());
            epreuveDto.setTypeEpreuve(epreuve.getTypeEpreuve());
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
        return epreuveDto;
    }

}