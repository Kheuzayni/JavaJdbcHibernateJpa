package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.entity.Match;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {

    public void create(Match match){
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


            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `match_tennis` (`ID_EPREUVE`, `ID_VAINQUEUR`, `ID_FINALISTE`) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            preparedStatement.executeUpdate();

            //recuperer Toutes les valeurs auto-générées  après l'enregistrement
            ResultSet rs=preparedStatement.getGeneratedKeys();

            System.out.println("Match créé avec succes");
            //fin ajout

            if (rs.next()){
                match.setId(rs.getLong(1));
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
