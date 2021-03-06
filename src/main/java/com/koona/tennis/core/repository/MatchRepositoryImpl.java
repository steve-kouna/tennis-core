package com.koona.tennis.core.repository;

import com.koona.tennis.core.DataSourceProvider;
import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.entity.MatchTennis;
import java.sql.*;
import javax.sql.*;
import org.hibernate.Session;

/**
 *
 * @author Steve KOUNA
 */
public class MatchRepositoryImpl {

    public void create(MatchTennis match) {
        Connection connection = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDtaSourceInstance();
            connection = dataSource.getConnection();

            String sqls = "INSERT INTO match_tennis (id_epreuve, id_vainqueur, id_finaliste) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            int nbre = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            
            if (rs.next()) {
                match.setId(rs.getLong(1));
            }

            System.out.println("Match cree : " + nbre);
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
    
    public MatchTennis getById (Long id) {
        MatchTennis matchTennis = new MatchTennis();
        Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        matchTennis = session.get(MatchTennis.class, id);
        System.out.println("Match de Tennis lu");
        
        return matchTennis;
    }
    
    public void deleteById(Long id) {
        
        Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        MatchTennis matchTennis = this.getById(id);
        matchTennis.setId(id);

        session.delete(matchTennis);
        
        System.out.println("Match de Tennis supprime");
    }
}
