/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.tennis.core.service;

import com.koona.tennis.core.entity.MatchTennis;
import com.koona.tennis.core.repository.MatchRepositoryImpl;
import com.koona.tennis.core.repository.ScoreVainqueurRepositoryImpl;

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
}
