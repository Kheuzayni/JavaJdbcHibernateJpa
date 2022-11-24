package com.mycompany.tennis.entity;


import javax.persistence.*;
import java.util.Set;

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


    @ManyToMany
    @JoinTable (
            name = "PARTICIPANTS",
            joinColumns = {@JoinColumn(name = "ID_EPREUVE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_JOUEUR")}
    )
    private Set <Joueur> participants;


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

    public Set<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Joueur> participants) {
        this.participants = participants;
    }
}
