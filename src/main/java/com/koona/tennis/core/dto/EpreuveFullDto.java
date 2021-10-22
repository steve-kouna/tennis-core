package com.koona.tennis.core.dto;

import com.koona.tennis.core.entity.Epreuve;

/**
 *
 * @author Steve KOUNA
 */
public class EpreuveFullDto{
    
    private Long id;
    
    private short annee;
    
    private Character typeEpreuve;

    private TournoiDto tournoi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }

    public short getAnnee() {
        return annee;
    }

    public void setAnnee(short annee) {
        this.annee = annee;
    }

    public TournoiDto getTournoi() {
        return tournoi;
    }

    public void setTournoi(TournoiDto tournoi) {
        this.tournoi = tournoi;
    }
    
    
}
