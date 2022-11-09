package com.mycompany.tennis;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class jdbcAppli {
        public static void main(String... args){
            Connection conn = null;
            try {
                //Utilisation de DataSource
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
                dataSource.setUser("root");
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

                String nom="Sy";
                String prenom="Aladji";
                String sexe="H";

                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, sexe);

                preparedStatement.executeUpdate();

                nom="Fall";
                prenom="Baye";
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

