package com.mycompany.tennis;

import com.mycompany.tennis.service.JoueurService;
import com.mycompany.tennis.service.TournoiService;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.entity.Tournoi;

public class TestServ {

    public static void main(String... args){
        JoueurService joueurService=new JoueurService();
        TournoiService tournoiService=new TournoiService();



        //Lecture Joueur avec Service
        Joueur FallYaram=joueurService.getJoueur(59L);
        System.out.println(FallYaram.getPrenom()+" "+FallYaram.getNom());

        //Lecture Tournoi Service
        Tournoi Wimbledo=tournoiService.getTournoi( 3L);
        System.out.println(Wimbledo.getNom()+" "+Wimbledo.getCode());


/*
        //Créer Joueur
        Joueur FallYaram = new Joueur();
        FallYaram.setNom("Fall");
        FallYaram.setPrenom("Baye");
        FallYaram.setSexe('H');
        joueurService.createJoueur(FallYaram);

        System.out.println("L'identifiant du joueur créé est "+FallYaram.getId());
*/

       /*
        //Créer Tournoi
        Tournoi FallYaram = new Tournoi();
        FallYaram.setNom("Tournoi1");
        FallYaram.setCode("T1");
        tournoiService.createTournoi(FallYaram);

        System.out.println("L'identifiant du tournoi créé est "+FallYaram.getId());
        */


     /* //Update Joueur
        Joueur FallYaram=joueurRepository.getById(58L);
        FallYaram.setPrenom("Yaramee");
        joueurRepository.update(FallYaram);
     */

     /* //Delete Joueur
        joueurRepository.delete(58L);
     */

     /*
        //Retourner Liste de joueurs
        List <Joueur> liste = joueurRepository.list();

        for (Joueur joueur : liste){
            System.out.println(joueur.getPrenom()+" "+joueur.getNom());
        }
     */

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
