package com.mycompany.tennis;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");

            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","root","");
            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","root","");

            //Lire les enregistrements de ma base
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("SELECT NOM, PRENOM, ID FROM JOUEUR");

                while (rs.next()){
                    final String prenom = rs.getNString("PRENOM");
                    final String nom = rs.getNString("NOM");
                    final Long id= rs.getLong("ID");
                    System.out.println("Le (la) joueur (euse) " +id+" est representé(e) par "+prenom+" "+nom);
                }
            //fin lecture des enregistrements de ma base

        /*
            //Recupérer avec un enregistrement précis
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("SELECT NOM, PRENOM, ID FROM JOUEUR WHERE ID=12");

                if (rs.next()){
                    final String prenom = rs.getNString("PRENOM");
                    final String nom = rs.getNString("NOM");
                    final Long id= rs.getLong("ID");
                    System.out.println("Le (la) joueur (euse) " +id+" est representé(e) par "+prenom+" "+nom);
                }
                else {
                    System.out.println("Il n'y a pas d'enregistrement avec l'id 12");
                }
            //Fin lecture avec un enregistrement précis
         */

            //Test connexion
            System.out.println("\n success ame nga ndy accès si bdd bi");

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

