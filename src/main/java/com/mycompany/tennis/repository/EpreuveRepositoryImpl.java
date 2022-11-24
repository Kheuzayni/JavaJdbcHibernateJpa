package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;

import com.mycompany.tennis.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class EpreuveRepositoryImpl {

    //Lire les informations du tournoi
    public Epreuve getById(Long id){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Epreuve epreuve = session.get(Epreuve.class, id);
        System.out.println("Epreuve lu");

        return epreuve;
    }
    public List<Epreuve> list(String codeTournoi){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Epreuve> query = session.createQuery("select e from Epreuve e where e.tournoi.codeTournoi =?0", Epreuve.class);
        query.setParameter(0,codeTournoi);
        List<Epreuve> epreuves =query.getResultList() ;

        System.out.println("Liste des epreuves lus");
        return epreuves;


    }
