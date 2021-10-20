package com.koona.tennis.core.repository;

import com.koona.tennis.core.DataSourceProvider;
import com.koona.tennis.core.HibernateUtil;
import com.koona.tennis.core.entity.ScoreVainqueur;
import java.sql.*;
import javax.sql.*;
import org.hibernate.Session;

/**
 *
 * @author Steve KOUNA
 */
public class ScoreVainqueurRepositoryImpl {

    public void create(ScoreVainqueur score) {
        Connection connection = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDtaSourceInstance();
            connection = dataSource.getConnection();

            String sqls = "INSERT INTO score_vainqueur (id_match, set_1, set_2, set_3, set_4, set_5) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, score.getMatch().getId());
            preparedStatement.setByte(2, score.getSet1());
            preparedStatement.setByte(3, score.getSet2());
            
            if (score.getSet3() == null) {
                preparedStatement.setNull(4, Types.TINYINT);
            } else {
                preparedStatement.setByte(4, score.getSet3());
            }
            
            if (score.getSet4() == null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5, score.getSet4());
            }
            
            if (score.getSet4() == null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5, score.getSet4());
            }
            
            if (score.getSet5() == null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(6, score.getSet5());
            }

            int nbre = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            
            if (rs.next()) {
                score.setId(rs.getLong(1));
            }

            System.out.println("Score cree : " + nbre);
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
    
    public ScoreVainqueur getById(long id) {
        ScoreVainqueur scoreVainqueur = null;
        Session session = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        scoreVainqueur = session.get(ScoreVainqueur.class, id);
        
        System.out.println("Score lu !");
        return scoreVainqueur;
    }
}
