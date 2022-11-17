package com.mycompany.tennis.entity;


import javax.persistence.*;

public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short annee;
    @Column(name = "TYPE_EPREUVE")
    private Character TypeEpreuve;
//    @Transient
    @ManyToOne
    @JoinColumn(name = "ID_TOURNOI")
    private Tournoi tournoi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Character getTypeEpreuve() {
        return TypeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        TypeEpreuve = typeEpreuve;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }
}
