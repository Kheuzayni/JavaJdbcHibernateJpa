package com.mycompany.tennis.service;

import com.mycompany.tennis.Dto.JoueurDto;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JoueurService {
    private JoueurRepositoryImpl joueurRepository;

    public JoueurService (){

        this.joueurRepository = new JoueurRepositoryImpl();
    }


    //methode qui retourne la liste de joueurs
    public List<JoueurDto> getListeJoeurs(char sexe){
        Session session=null;
        Transaction tx=null;

        List<JoueurDto> joueurDtos = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            List<Joueur> joueurs = joueurRepository.list(sexe);

            for (Joueur joueur: joueurs){
                final JoueurDto dto = new JoueurDto();
                dto.setId(joueur.getId());
                dto.setNom(joueur.getNom());
                dto.setPrenom(joueur.getPrenom());
                dto.setSexe(joueur.getSexe());
                dtos.add(dto);   //dtos dans JoueurDto
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

    public void createJoueur (Joueur joueur){
        Session session=null;
        Transaction tx=null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.create(joueur);
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


    //Lecture joueur
    public Joueur getJoueur(Long id) {
        Session session=null;
        Transaction tx=null;
        Joueur joueur = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.getById(id);
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
        return joueur;
    }

    public void renomme(Long id, String NouveauNom){
          Joueur joueur = getJoueur(id);
          Session session=null;
          Transaction tx=null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setNom(NouveauNom);
            Joueur joueur2 = (Joueur)session.merge(joueur);
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

    public void deleteJoueur(Long id){
          Session session = null;
          Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.delete(id);
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
