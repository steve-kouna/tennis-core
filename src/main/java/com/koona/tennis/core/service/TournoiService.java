package com.koona.tennis.core.service;

import com.koona.tennis.core.entity.Tournoi;
import com.koona.tennis.core.repository.TournoiRepositoryImpl;


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
}
