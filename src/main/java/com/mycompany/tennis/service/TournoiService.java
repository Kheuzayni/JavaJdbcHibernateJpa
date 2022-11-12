package com.mycompany.tennis.service;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.entity.Tournoi;
import com.mycompany.tennis.repository.TournoiRepositoryImpl;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;
    public TournoiService (){
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void createTournoi (Tournoi tournoi){
        tournoiRepository.create(tournoi);
    }



    //Lecture tournoi
    public Tournoi getTournoi(Long id) {
        return tournoiRepository.getById(id);
    }

}
