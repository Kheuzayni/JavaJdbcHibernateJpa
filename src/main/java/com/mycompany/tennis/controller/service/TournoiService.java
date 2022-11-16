package com.mycompany.tennis.controller.service;

import com.mycompany.tennis.controller.HibernateUtil;
import com.mycompany.tennis.controller.entity.Joueur;
import com.mycompany.tennis.controller.entity.Tournoi;
import com.mycompany.tennis.controller.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.controller.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;
    public TournoiService (){
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void createTournoi (Tournoi tournoi){

        Session session=null;
        Transaction tx=null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepository.create(tournoi);
            tx.commit();
        }
        catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }

    }

    //Lecture tournoi
    public Tournoi getTournoi(Long id) {

        Session session=null;
        Transaction tx=null;
        Tournoi tournoi= null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepository.getById(id);
            tx.commit();
        }
        catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
        return tournoi;
    }

    public void deleteTournoi(Long id){

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepository.delete(id);
            tx.commit();
        }
        catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
    }

}
