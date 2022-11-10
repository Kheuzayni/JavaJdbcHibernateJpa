package com.mycompany.tennis;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;

import java.util.List;

public class Testripo {
    public static void main(String... args){
        JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();

        //Lecture
        Joueur Cheikh=joueurRepository.getById(57L);
        System.out.println(Cheikh.getPrenom()+" "+Cheikh.getNom());
     /*
        //Créer Joueur
        Joueur FallYaram = new Joueur();
        FallYaram.setNom("Fall");
        FallYaram.setPrenom("Yaram");
        FallYaram.setSexe('H');
        joueurRepository.create(FallYaram);
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
        //Créer Joueur en affichant l'identifiant
        Joueur FallYaram = new Joueur();
        FallYaram.setNom("Fall");
        FallYaram.setPrenom("Yaram");
        FallYaram.setSexe('H');
        joueurRepository.create(FallYaram);
        System.out.println("L'identifiant du jouer ("+FallYaram.getPrenom() +" "+ FallYaram.getNom()+") créé est "+FallYaram.getId());
    }
}
