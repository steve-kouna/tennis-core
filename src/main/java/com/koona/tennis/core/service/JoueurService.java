package com.koona.tennis.core.service;

import com.koona.tennis.core.EntityManagerHolder;
import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.dto.JoueurDto;
import com.koona.tennis.core.entity.Joueur;
import com.koona.tennis.core.repository.JoueurRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepositoryImpl.create(joueur);
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
    }

    public Joueur getJoueur(Long id) {
        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepositoryImpl.readOne(id);
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
        return joueur;
    }

    public void rename(Long id, String name) {
//        joueurRepositoryImpl.rename(id, name);
        Joueur joueur = getJoueur(id);
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur.setNom(name);
            session.merge(joueur);
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
    }
    
    public void changeSexe(Long id, char sexe) {
//        joueurRepositoryImpl.rename(id, name);
        Joueur joueur = getJoueur(id);
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur.setSexe(sexe);
            session.merge(joueur);
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
    }
    
    public void deleteJoueur(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepositoryImpl.delete(id);
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
    }
    
    public List<JoueurDto> getAll(char sexe){
        EntityManager em = null;
        EntityTransaction tx = null;
        List<JoueurDto> joueurDtos = new ArrayList<>();
        try {
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            List<Joueur> joueurs = joueurRepositoryImpl.readAll(sexe);
            for (Joueur joueur: joueurs){
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setSexe(joueur.getSexe());
                
                joueurDtos.add(joueurDto);
            }
            tx.commit();
         
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return joueurDtos;
    }
    
}
