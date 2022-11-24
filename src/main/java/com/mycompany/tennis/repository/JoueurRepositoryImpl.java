package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur){
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
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `joueur` (`NOM`, `PRENOM`, `SEXE`) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            String nom="Diop";
            String prenom="Samba";
            String sexe="H";

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());

            preparedStatement.executeUpdate();

            //recuperer Toutes les valeurs auto-générées  après l'enregistrement
            ResultSet rs=preparedStatement.getGeneratedKeys();

            System.out.println("Joueur ajouté avec succes");
            //fin ajout

            if (rs.next()){
                joueur.setId(rs.getLong(1));
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
    //Update
    public void update(Joueur joueur){

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
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `joueur` SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());

            preparedStatement.executeUpdate();


            System.out.println("Joueur modifié avec succes");
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
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM `joueur` WHERE ID=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();


            System.out.println("Joueur supprimé avec succes");
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

    //Lire les informations du joueur
    public Joueur getById(Long id){

        Joueur joueur=null;
        Session session=null;

        try {
            //On dira à Hibernate de nous reserver l'espace pour l'instant
            session = HibernateUtil.getSessionFactory().openSession();
            //On recupére depuis la bdd
            joueur = session.get(Joueur.class,id);

            System.out.println("Joueur Lu");
        }
        catch ( Throwable t){
            t.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }

/*   On remplace le code actuel en utilisant HibernateUtil
        Connection conn = null;
        Joueur joueur = null; //On intancie
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //Test connexion
            System.out.println("\n success Test acces bdd");

            //
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM `joueur` WHERE ID=?");

            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                joueur=new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0)); //charAt pour  prendre que l& 1ere caracatère
            }
            System.out.println("Joueur lu");
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
        */
        return joueur;

    }

    //Methode liste de joueur
    public List<Joueur> list(){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query = session.createQuery("select j from Joueur j", Joueur.class);
   //   Query<Joueur> query = session.createQuery("from Joueur", Joueur.class);
        List<Joueur> joueurs =query.getResultList() ;

        System.out.println("Liste des Joueurs lus");
        return joueurs;

/*
        Connection conn = null;
        List<Joueur> joueurs=new ArrayList<>(); //On intancie

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //Test connexion
            System.out.println("\n success Test acces bdd");

            //
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID,NOM,PRENOM,SEXE FROM `joueur` ");

           // preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Joueur joueur = new Joueur();
                joueur=new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0)); //charAt pour  prendre que l& 1ere caracatère
                joueurs.add(joueur);
            }
            System.out.println("Liste des Joueurs lus");
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
        return joueurs;*/
    }
}
