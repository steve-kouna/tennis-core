package com.koona.tennis.core.repository;

import com.koona.tennis.core.DataSourceProvider;
import com.koona.tennis.core.EntityManagerHolder;
import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.dto.TournoiDto;
import com.koona.tennis.core.entity.Tournoi;
import java.sql.*;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Steve KOUNA
 */
public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi) {
//        Session session = null;
        EntityTransaction tx = null;

//        session = HibernateUtil.getSessionFactory().openSession();
        EntityManager em = new EntityManagerHolder().getCurrentEntityManager();
        tx = em.getTransaction();
        tx.begin();
        em.persist(tournoi);
        tx.commit();

        System.out.println("Joueur cree : " + tournoi.getId());
    }

    public Tournoi readOne(Long id) {
        Tournoi tournoi = null;

//        Session session = null;
//
//        session = HibernateUtil.getSessionFactory().openSession();
        EntityManager em = new EntityManagerHolder().getCurrentEntityManager();
        
        tournoi = em.find(Tournoi.class, id);
        System.out.println("Tournoi lu");

        return tournoi;
    }

    public List<Tournoi> readAll() {
        List<Tournoi> tournois = new ArrayList();

        Connection connection = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDtaSourceInstance();
            connection = dataSource.getConnection();

            String sqls = "SELECT * FROM TOURNOI";
            PreparedStatement preparedStatement = connection.prepareStatement(sqls);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournoi.setId(rs.getLong("ID"));
                tournois.add(tournoi);
            }

            System.out.println("Tournois lus");
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

        return tournois;
    }

    public void update(Tournoi tournoi, Long id) {
        Connection connection = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDtaSourceInstance();
            connection = dataSource.getConnection();

            String sqls = "UPDATE TOURNOI SET NOM = ?, CODE = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqls);
            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.setLong(3, id);
            int nbre = preparedStatement.executeUpdate();

            System.out.println("Tournoi modifie : " + nbre);
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

    public void delete(Long id) {
        EntityManager em = new EntityManagerHolder().getCurrentEntityManager();
        Tournoi tournoi = em.find(Tournoi.class, id);
        em.remove(tournoi);
        
        System.out.println("Tournoi supprime");
    }
}
