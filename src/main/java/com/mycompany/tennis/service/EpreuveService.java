package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;
    public EpreuveService (){

        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    //Lecture tournoi
    public Epreuve getEpreuve(Long id) {

        Session session=null;
        Transaction tx=null;
        Epreuve epreuve= null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuveRepository.getById(id);
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
        return epreuve;
    }

}
