package com.mycompany.tennis.controller.service;

import com.mycompany.tennis.controller.entity.Tournoi;
import com.mycompany.tennis.controller.repository.TournoiRepositoryImpl;

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
