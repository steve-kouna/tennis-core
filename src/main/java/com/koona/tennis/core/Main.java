/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.tennis.core;

import com.koona.tennis.core.entity.Epreuve;
import com.koona.tennis.core.entity.Joueur;
import com.koona.tennis.core.entity.MatchTennis;
import com.koona.tennis.core.entity.ScoreVainqueur;
import com.koona.tennis.core.service.JoueurService;
import com.koona.tennis.core.service.MatchService;

/**
 *
 * @author Steve KOUNA
 */
public class Main {
    
    public static void main(String... args) {
        MatchService matchService = new MatchService();
        MatchTennis match = new MatchTennis();
        ScoreVainqueur score = new ScoreVainqueur();
        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);
        
        match.setScore(score);
        score.setMatch(match);
        
        Joueur federer = new Joueur();
        federer.setId(32L);
        
        Joueur murray = new Joueur();
        murray.setId(34L);
        
        match.setVainqueur(federer);
        match.setFinaliste(murray);
        
        Epreuve epreuve = new Epreuve();
        epreuve.setId(10L);
        match.setEpreuve(epreuve);
        
        matchService.saveNewMatch(match);
        
        System.out.println("L'identifiant du match cree est : " + match.getId());
    }
}
