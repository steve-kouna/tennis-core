package com.koona.tennis.core.dto;


/**
 *
 * @author Steve KOUNA
 */
public class MatchTennisDto {
    
    private Long id;
    
    private JoueurDto vainqueur;
    
    private JoueurDto finaliste;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JoueurDto getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(JoueurDto vainqueur) {
        this.vainqueur = vainqueur;
    }

    public JoueurDto getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(JoueurDto finaliste) {
        this.finaliste = finaliste;
    }
    
}