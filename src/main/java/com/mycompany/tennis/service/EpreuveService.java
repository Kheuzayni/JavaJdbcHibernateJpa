package com.mycompany.tennis.service;

import com.mycompany.tennis.Dto.EpreuveDto;
import com.mycompany.tennis.Dto.JoueurDto;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;
    public EpreuveService (){

        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    //Lecture tournoi
    public Epreuve getEpreuveAvecTournoi(Long id) {

        Session session=null;
        Transaction tx=null;
        Epreuve epreuve= null;
        EpreuveDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);
            Hibernate.initialize(epreuve.getTournoi());
            tx.commit();
            dto= new EpreuveDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
            dto.setTournoi(epreuve.getTournoi());
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
        return dto;
    }

    public Epreuve getEpreuveSansTournoi(Long id) {

        Session session=null;
        Transaction tx=null;
        Epreuve epreuve= null;
        EpreuveDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);
            tx.commit();
            dto= new EpreuveDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
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
        return dto;
    }

    public List<EpreuveDto> getListeEpreuve(String codeTournoi){
        Session session=null;
        Transaction tx=null;

        List<EpreuveDto> epreuveDtos = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            List<Epreuve> epreuves = epreuveRepository.list(codeTournoi);

            for (Epreuve epreuve: epreuves){
                final EpreuveDto dto = new EpreuveDto();
                dto.setId(epreuve.getId());
                dto.setAnnee(epreuve.getAnnee());
                dto.setTypeEpreuve(epreuve.getTypeEpreuve());
                dto.setTournoi(epreuve.getTournoi());
                dtos.add(dto);   //dtos à ajouté dans EpreuveDto
            }

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
        return dtos;
    }
}
