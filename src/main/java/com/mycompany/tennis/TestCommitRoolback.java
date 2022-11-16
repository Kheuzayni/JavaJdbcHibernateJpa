package com.mycompany.tennis;

import java.sql.*;

public class TestCommitRoolback {
    public static void main(String... args){
        Connection conn = null;
        try {
            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
            //Test connexion
            System.out.println("\n success Test acces bdd");

         //Personnaliser le autocommit
            conn.setAutoCommit(false); //false pour dire au commit d'attendre un Go pour la validation
            //Update enregistrement
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `joueur` (`NOM`, `PRENOM`, `SEXE`) VALUES (?, ?, ?);");

            String nom="Ba";
            String prenom="Adama";
            String sexe="H";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            nom="Diop";
            prenom="Fatou";
            sexe="F";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            nom="Diop";
            prenom="Assane";
            sexe="F";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setNull(3, Types.CHAR); //rendre le sexe null qui générera une erreur

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

