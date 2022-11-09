package com.mycompany.tennis;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;

public class Testripo {
    public static void main(String... args){
        JoueurRepositoryImpl joueurRepository=new JoueurRepositoryImpl();
        Joueur Cheikh=joueurRepository.getById(57L);
        System.out.println(Cheikh.getPrenom()+" "+Cheikh.getNom());
    }
}
