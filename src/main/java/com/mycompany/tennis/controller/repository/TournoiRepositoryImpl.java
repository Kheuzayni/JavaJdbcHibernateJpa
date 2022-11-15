package com.mycompany.tennis.controller.repository;

import com.mycompany.tennis.controller.DataSourceProvider;
import com.mycompany.tennis.controller.entity.Tournoi;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

      //Créer Tournoi
        public void create(Tournoi tournoi){
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

                //
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `tournoi` (`NOM`, `CODE`) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);

                String nom="Tournoi1";
                String code="T1";

                preparedStatement.setString(1, tournoi.getNom());
                preparedStatement.setString(2, tournoi.getCode());

                preparedStatement.executeUpdate();

                //recuperer Toutes les valeurs auto-générées  après l'enregistrement
                ResultSet rs=preparedStatement.getGeneratedKeys();

                System.out.println("Tournoi ajouté avec succes");
                //fin ajout

                if (rs.next()){
                    tournoi.setId(rs.getLong(1));
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

    //Update tournoi
    public void update(Tournoi tournoi){

        Connection conn = null;
        try {
            //Pool de connexion avec DBCP
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            dataSource.setUsername("root");
            dataSource.setPassword("");
            conn = dataSource.getConnection();


            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `tournoi` SET NOM=?, CODE=? WHERE ID=?");

            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.setLong(3, tournoi.getId());

            preparedStatement.executeUpdate();


            System.out.println("Tournoi modifié avec succes");
            //fin modification

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
    //Delete
    public void delete(Long id){

        Connection conn = null;
        try {
            //depuis DataSourceProvider
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM `tournoi` WHERE ID=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();


            System.out.println("Tournoi supprimé avec succes");
            //fin modification

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

    //Lire les informations du tournoi
    public Tournoi getById(Long id){

        Connection conn = null;
        Tournoi tournoi = null; //On intancie
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, CODE FROM `tournoi` WHERE ID=?");

            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                tournoi=new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
            }
            System.out.println("Tournoi lu");
            //fin modification

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
        return tournoi;
    }

    //Methode liste de tournoi
    public List<Tournoi> list(){

        Connection conn = null;
        List<Tournoi> tournois=new ArrayList<>(); //On intancie

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //Test connexion
            System.out.println("\n success Test acces bdd");

            //
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,CODE FROM `tournoi` ");

            // preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Tournoi tournoi = new Tournoi();
                tournoi=new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }
            System.out.println("Liste des Tournoi lus avec succes");
            //fin modification

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
        return tournois;
    }
}
