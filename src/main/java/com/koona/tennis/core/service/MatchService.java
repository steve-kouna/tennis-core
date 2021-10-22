/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.tennis.core.service;

import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.dto.JoueurDto;
import com.koona.tennis.core.dto.MatchTennisDto;
import com.koona.tennis.core.entity.MatchTennis;
import com.koona.tennis.core.repository.MatchRepositoryImpl;
import com.koona.tennis.core.repository.ScoreVainqueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Steve KOUNA
 */
public class MatchService {
    
    private ScoreVainqueurRepositoryImpl scoreVainqueurRepositoryImpl;
    private MatchRepositoryImpl matchRepositoryImpl; 

    public MatchService() {
        this.scoreVainqueurRepositoryImpl = new ScoreVainqueurRepositoryImpl();
        this.matchRepositoryImpl = new MatchRepositoryImpl(); 
    }
    
    
    public void saveNewMatch(MatchTennis match) {
        matchRepositoryImpl.create(match);
        scoreVainqueurRepositoryImpl.create(match.getScore());
    }
    
    public MatchTennisDto getMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        MatchTennis matchTennis = null;
        MatchTennisDto matchTennisDto = new MatchTennisDto();
        
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            
            matchTennis = matchRepositoryImpl.getById(id);
            
            matchTennisDto.setId(matchTennis.getId());
            
            JoueurDto vainqueur = new JoueurDto();
            vainqueur.setId(matchTennis.getVainqueur().getId());
            vainqueur.setNom(matchTennis.getVainqueur().getNom());
            vainqueur.setPrenom(matchTennis.getVainqueur().getPrenom());
            vainqueur.setSexe(matchTennis.getVainqueur().getSexe());
            
            JoueurDto finaliste = new JoueurDto();
            finaliste.setId(matchTennis.getFinaliste().getId());
            finaliste.setNom(matchTennis.getFinaliste().getNom());
            finaliste.setPrenom(matchTennis.getFinaliste().getPrenom());
            finaliste.setSexe(matchTennis.getFinaliste().getSexe());
           
            
            matchTennisDto.setVainqueur(vainqueur);
            matchTennisDto.setFinaliste(finaliste);
                    
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
        
    return matchTennisDto;
    }
}
