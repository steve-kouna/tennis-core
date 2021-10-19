package com.koona.tennis.core.service;

import com.koona.tennis.core.entity.Joueur;
import com.koona.tennis.core.repository.JoueurRepositoryImpl;

/**
 *
 * @author Steve KOUNA
 */
public class JoueurService {
    
    private JoueurRepositoryImpl joueurRepositoryImpl;


    public JoueurService() {
        this.joueurRepositoryImpl = new JoueurRepositoryImpl();
    }
    
    
    public void createJoueur(Joueur joueur) {
        joueurRepositoryImpl.create(joueur);
    }
    
    public Joueur getJoueur (Long id) {
        Joueur joueur = joueurRepositoryImpl.readOne(id);
        
        return joueur;
    }
    
    public void rename(Long id, String name){
        joueurRepositoryImpl.rename(id, name);
    }
}
