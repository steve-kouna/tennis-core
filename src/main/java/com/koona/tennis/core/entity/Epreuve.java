package com.koona.tennis.core.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;

/**
 *
 * @author Steve KOUNA
 */
@Entity
public class Epreuve {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Type(type="short")
    private short annee;
    
    @Column(name="type_epreuve")
    private Character typeEpreuve;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tournoi")
    private Tournoi tournoi;
    
    @ManyToMany
    @JoinTable(
            name = "participants",
            joinColumns = {
                @JoinColumn(name = "id_epreuve")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "id_joueur")
            }
    )
    private Set<Joueur> participants;

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

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public short getAnnee() {
        return annee;
    }

    public void setAnnee(short annee) {
        this.annee = annee;
    }

    public Set<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Joueur> participants) {
        this.participants = participants;
    }
    
    
}
