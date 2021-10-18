package com.koona.tennis.core.repository;

import com.koona.tennis.core.DataSourceProvider;
import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.entity.Tournoi;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;
import org.hibernate.Session;

/**
 *
 * @author Steve KOUNA
 */
public class TournoiRepositoryImpl {
    
    public void create(Tournoi tournoi) {
        Connection connection = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDtaSourceInstance();
            connection = dataSource.getConnection();

            String sqls = "INSERT INTO TOURNOI (NOM, CODE) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());

            int nbre = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            
            if (rs.next()) {
                tournoi.setId(rs.getLong(1));
            }

            System.out.println("Tournoi cree : " + nbre);
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
    
    public Tournoi readOne(Long id) {
        Tournoi tournoi = new Tournoi();
        
//        Connection connection = null;
        Session session = null;
        try {
//            DataSource dataSource = DataSourceProvider.getSingleDtaSourceInstance();
//            connection = dataSource.getConnection();
//
//            String sqls = "SELECT * FROM TOURNOI WHERE ID = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sqls);
//            preparedStatement.setLong(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                tournoi.setNom(rs.getString("NOM"));
//                tournoi.setCode(rs.getString("CODE"));
//                tournoi.setId(rs.getLong("ID"));
//            }
            session = HibernateUtil.getSessionFactory().openSession();
            tournoi = session.get(Tournoi.class, id);
            System.out.println("Tournoi lu");
        } 
//        catch (SQLException e) {
//            e.printStackTrace();
//        } 
        finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        
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
        Connection connection = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDtaSourceInstance();
            connection = dataSource.getConnection();

            String sqls = "DELETE FROM TOURNOI WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqls);
            preparedStatement.setLong(1, id);
            int rs = preparedStatement.executeUpdate();

            System.out.println("Tournoi supprime : " + rs);
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
}
