/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.tennis.core.service;

import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.dto.EpreuveFullDto;
import com.koona.tennis.core.dto.MatchTennisDto;
import com.koona.tennis.core.dto.ScoreVainqueurFullDto;
import com.koona.tennis.core.dto.TournoiDto;
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
    
    
    public ScoreVainqueurFullDto getScore(Long id) {
        Session session = null;
        Transaction tx = null;
        ScoreVainqueur scoreVainqueur = null;
        ScoreVainqueurFullDto scoreVainqueurFullDto = new ScoreVainqueurFullDto();
        
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            scoreVainqueur = scoreVainqueurRepositoryImpl.getById(id);
            
            scoreVainqueurFullDto.setId(scoreVainqueur.getId());
            scoreVainqueurFullDto.setSet1(scoreVainqueur.getSet1());
            scoreVainqueurFullDto.setSet2(scoreVainqueur.getSet2());
            scoreVainqueurFullDto.setSet3(scoreVainqueur.getSet3());
            scoreVainqueurFullDto.setSet4(scoreVainqueur.getSet4());
            scoreVainqueurFullDto.setSet5(scoreVainqueur.getSet5());
            
            MatchTennisDto matchTennisDto = new MatchTennisDto();
            matchTennisDto.setId(scoreVainqueur.getMatch().getId());
            
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(scoreVainqueur.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setNom(scoreVainqueur.getMatch().getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(scoreVainqueur.getMatch().getEpreuve().getTournoi().getCode());
            
            EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
            epreuveFullDto.setAnnee(scoreVainqueur.getMatch().getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(scoreVainqueur.getMatch().getEpreuve().getTypeEpreuve());
            epreuveFullDto.setTournoi(tournoiDto);
            
            matchTennisDto.setEpreuve(epreuveFullDto);
            
            scoreVainqueurFullDto.setMatch(matchTennisDto);
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
        return scoreVainqueurFullDto;
    }

}
