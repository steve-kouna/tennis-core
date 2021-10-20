/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.tennis.core.service;

import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.entity.ScoreVainqueur;
import com.koona.tennis.core.repository.ScoreVainqueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Steve KOUNA
 */
public class ScoreVainqueurService {
    
    private ScoreVainqueurRepositoryImpl scoreVainqueurRepositoryImpl;

    public ScoreVainqueurService() {
        this.scoreVainqueurRepositoryImpl = new ScoreVainqueurRepositoryImpl();
    }
    
    
    public ScoreVainqueur getScore(Long id) {
        Session session = null;
        Transaction tx = null;
        ScoreVainqueur scoreVainqueur = null;
        
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            scoreVainqueur = scoreVainqueurRepositoryImpl.getById(id);
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
        return scoreVainqueur;
    }

}
