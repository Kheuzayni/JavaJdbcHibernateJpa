package com.mycompany.tennis;

import java.sql.*;

public class TestUpdate {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
            //Test connexion
            System.out.println("\n success Test acces bdd");

            //Update enregistrement
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?,PRENOM=? WHERE ID=?");

            long identifiant =1L;
            String nom="FALL";
            String prenom="Baye Cheikh";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setLong(3, identifiant);

            int nbEnregisrementModifies = preparedStatement.executeUpdate();

            System.out.println("nombre d'enregisrement modifiés = "+ nbEnregisrementModifies);
            //fin Update enregistrement

        } catch (SQLException e) {
            e.printStackTrace();
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

