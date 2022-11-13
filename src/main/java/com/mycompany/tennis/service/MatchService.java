package com.mycompany.tennis.service;

import com.mycompany.tennis.DAO.MatchDaoImpl;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;

public class MatchService {
  /*
    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
  */
  //Utiliser Dao à la place des deux repository
    private MatchDaoImpl matchDao;

    public MatchService(){

        /*
            this.scoreRepository = new ScoreRepositoryImpl();
            this.matchRepository = new MatchRepositoryImpl();
        */
        //Utiliser Dao à la place des deux repository. On l'intencie
        matchDao = new MatchDaoImpl();
    }

    public void enregistrerNouveauMatch(Match match){
        /*
            matchRepository.create(match);
            scoreRepository.create(match.getScore());
        */
        matchDao.createMatchWithScore(match);
    }
}
