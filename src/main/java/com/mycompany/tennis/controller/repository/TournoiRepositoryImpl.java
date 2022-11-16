package com.mycompany.tennis.controller.repository;

import com.mycompany.tennis.controller.DataSourceProvider;
import com.mycompany.tennis.controller.HibernateUtil;
import com.mycompany.tennis.controller.entity.Tournoi;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {
    //Créer Tournoi
        public void create(Tournoi tournoi){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(tournoi);
        System.out.println("Tournoi créé");
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
            Tournoi tournoi = new Tournoi();
            tournoi.setId(id);
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.delete(tournoi);
            System.out.println("Tournoi supprimé");
    }

    //Lire les informations du tournoi
    public Tournoi getById(Long id){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Tournoi tournoi = session.get(Tournoi.class, id);
        System.out.println("Tournoi lu");

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
