package com.mycompany.tennis.repository;

import com.mycompany.tennis.entity.Score;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class ScoreRepositoryImpl {

    public void create(Score score){
        //On prend nos tests de TestPoolConnexion

        Connection conn = null;
        try {
            //Pool de connexion avec DBCP
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            dataSource.setUsername("root");
            dataSource.setPassword("");
            conn = dataSource.getConnection();

            //Test connexion
            System.out.println("\n success Test acces bdd");


            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `score_vainqueur` (`ID_MATCH`, `SET_1`, `SET_2`, `SET_3`, `SET_4`, `SET_5`) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setLong(1, score.getMatch().getId());
            preparedStatement.setByte(2, score.getSet1());
            preparedStatement.setByte(3, score.getSet2());
            if (score.getSet3() == null){
                preparedStatement.setNull(4,Types.TINYINT);
            }
            else{
                preparedStatement.setLong(4, score.getSet3());
            }

            if (score.getSet4() == null){
                preparedStatement.setNull(5,Types.TINYINT);
            }
            else{
                preparedStatement.setLong(5, score.getSet5());
            }
            if (score.getSet5() == null){
                preparedStatement.setNull(6,Types.TINYINT);
            }
            else{
                preparedStatement.setLong(6, score.getSet5());
            }



            preparedStatement.executeUpdate();

            //recuperer Toutes les valeurs auto-générées  après l'enregistrement
            ResultSet rs=preparedStatement.getGeneratedKeys();

            System.out.println("Score enrégistré avec succes");
            //fin ajout

            if (rs.next()){
                score.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn!=null) conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
