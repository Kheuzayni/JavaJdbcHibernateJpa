package com.mycompany.tennis;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPoolConnexion {

        public static void main(String... args){
            Connection conn = null;
            try {
                //Pool de connexion avec DBCP
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
                dataSource.setUsername("root");
                dataSource.setPassword("");
                conn = dataSource.getConnection();

                //MySQL driver MySQL Connector

                //Remplacer par MysqlDatasource             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
                //Test connexion
                System.out.println("\n success Test acces bdd");

                //Personnaliser le autocommit
                conn.setAutoCommit(false); //false pour dire au commit d'attendre un Go pour la validation
                //Update enregistrement
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `joueur` (`NOM`, `PRENOM`, `SEXE`) VALUES (?, ?, ?);");

                String nom="Diop";
                String prenom="Samba";
                String sexe="H";

                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, sexe);

                preparedStatement.executeUpdate();

                nom="FAll";
                prenom="Cheikh";
                sexe="H";

                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, sexe);

                preparedStatement.executeUpdate();

                conn.commit(); //Pour lui donner le Go pour qu'il puisse passer à la validation

                System.out.println("Success insertion");
                //fin Update enregistrement

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (conn!=null) conn.rollback(); //Si il y a une erreur inattendu on fait un retour pour tout annuler
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


