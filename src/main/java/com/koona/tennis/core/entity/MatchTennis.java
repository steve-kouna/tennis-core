package com.koona.tennis.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Steve KOUNA
 */
@Entity
@Table(name = "match_tennis")
public class MatchTennis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Transient
    private Epreuve epreuve;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vainqueur")
    private Joueur vainqueur;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_finaliste")
    private Joueur finaliste;
    
    @Transient
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
