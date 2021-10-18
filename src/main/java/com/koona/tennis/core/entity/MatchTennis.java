package com.koona.tennis.core.entity;

/**
 *
 * @author Steve KOUNA
 */
public class MatchTennis {
    
    private Long id;
    private Epreuve epreuve;
    private Joueur vainqueur;
    private Joueur finaliste;
    private ScoreVainqueur score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        this.finaliste = finaliste;
    }

    public ScoreVainqueur getScore() {
        return score;
    }

    public void setScore(ScoreVainqueur score) {
        this.score = score;
    }
    
}
