package com.mycompany.tennis.entity;

public class Match {
    private Long id;
    private Joueur Vainqueur;
    private Joueur Finaliste;
    private Epreuve epreuve;
    private Score score;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Joueur getVainqueur() {
        return Vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        Vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return Finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        Finaliste = finaliste;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
