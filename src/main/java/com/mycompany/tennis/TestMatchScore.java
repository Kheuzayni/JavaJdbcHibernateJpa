package com.mycompany.tennis;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.service.MatchService;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;

public class TestMatchScore {

    public static void main(String... args){
        //Créer Match
        MatchService matchService = new MatchService();
        Match match = new Match();
        Score score = new Score();
        score.setSet1((byte) 3);
        score.setSet2((byte) 4);
        score.setSet3((byte) 6);
        match.setScore(score);
        score.setMatch(match);

        Joueur errani=new Joueur();
        errani.setId(24L);
        Joueur stosur=new Joueur();
        stosur.setId(25L);

        match.setVainqueur(errani);
        match.setVainqueur(stosur);

        Epreuve epreuve=new Epreuve();
        epreuve.setId(9L);
        epreuve.setId(9L);
        match.setEpreuve(epreuve);

        matchService.enregistrerNouveauMatch(match);

        System.out.println("L'identification du match créée est "+match.getId());
    }

}
