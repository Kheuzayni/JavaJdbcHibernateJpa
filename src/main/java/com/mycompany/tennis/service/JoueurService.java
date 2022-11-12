package com.mycompany.tennis.service;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;

public class JoueurService {
      private JoueurRepositoryImpl joueurRepository;
      public JoueurService (){
            this.joueurRepository = new JoueurRepositoryImpl();
        }

    public void createJoueur (Joueur joueur){
          joueurRepository.create(joueur);
    }


    //Lecture joueur
    public Joueur getJoueur(Long id) {
        return joueurRepository.getById(id);
    }

}
