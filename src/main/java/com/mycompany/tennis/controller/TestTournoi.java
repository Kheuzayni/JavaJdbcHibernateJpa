package com.mycompany.tennis.controller;

import com.mycompany.tennis.controller.entity.Tournoi;
import com.mycompany.tennis.controller.repository.TournoiRepositoryImpl;

import java.util.List;

public class TestTournoi {

    public static void main(String... args){
        TournoiRepositoryImpl tournoiRepository=new TournoiRepositoryImpl();
/*

        //Lecture
        Tournoi Wimbledo=tournoiRepository.getById(3L);
        System.out.println(Wimbledo.getNom()+" "+Wimbledo.getCode());
*/


       /* //Créer Tournoi
        Tournoi FallYaram = new Tournoi();
        Wimbledo.setNom("Tournoi 1");
        Wimbledo.setCode("T1");
        tournoiRepository.create(Wimbledo);
        */

/*

      //Update Tournoi
        Tournoi Wimbledo=tournoiRepository.getById(3L);
        Wimbledo.setNom("Tournoi 2");
        tournoiRepository.update(Wimbledo);

*/

      //Delete Joueur
        tournoiRepository.delete(5L);



        //Retourner Liste de tournois
        List <Tournoi> liste = tournoiRepository.list();

        for (Tournoi tournoi : liste){
            System.out.println(tournoi.getNom()+" "+tournoi.getCode());
        }

        /*
        //Créer Joueur en affichant l'identifiant
        Joueur FallYaram = new Joueur();
        FallYaram.setNom("Fall");
        FallYaram.setPrenom("Yaram");
        FallYaram.setSexe('H');
        joueurRepository.create(FallYaram);
        System.out.println("L'identifiant du jouer ("+FallYaram.getPrenom() +" "+ FallYaram.getNom()+") créé est "+FallYaram.getId());
        */

    }
}
