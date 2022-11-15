package com.mycompany.tennis.controller.service;

import com.mycompany.tennis.controller.entity.Joueur;
import com.mycompany.tennis.controller.repository.JoueurRepositoryImpl;

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
