package com.koona.tennis.core.repository;

import com.koona.tennis.core.DataSourceProvider;
import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.entity.Joueur;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Steve KOUNA
 */
public class JoueurRepositoryImpl {

    public void rename(Long id, String newName) {
        Joueur joueur = new Joueur();
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            joueur = session.get(Joueur.class, id);
            joueur.setNom(newName);

            tx = session.beginTransaction();
            session.merge(joueur);
            tx.commit();
            System.out.println("Nom du Joueur modifie");
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
//        return joueur;
    }

    public void create(Joueur joueur) {
        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(joueur);

        System.out.println("Joueur cree : " + joueur.getId());
    }

    public void update(Joueur joueur, Long id) {
        Connection connection = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDtaSourceInstance();
            connection = dataSource.getConnection();

            String sqls = "UPDATE JOUEUR SET NOM = ?, PRENOM = ?, SEXE = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqls);
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, id);
            int nbre = preparedStatement.executeUpdate();

            System.out.println("Joueur modifie : " + nbre);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Joueur readOne(Long id) {
        Joueur joueur = null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class, id);

        System.out.println("Joueur lu");

        return joueur;
    }

    public void delete(Long id) {
        Joueur joueur = null;
        Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = readOne(id);
        session.delete(joueur);
    }

    public List<Joueur> readAll(char sexe) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query =  session.createNamedQuery("givenSexe", Joueur.class);
        query.setParameter(0, sexe);
        List<Joueur> joueurs = query.getResultList();
        
        System.out.println("Joueurs lus");
        
        return joueurs;
    }

}
