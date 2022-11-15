package com.mycompany.tennis.controller.service;

import com.mycompany.tennis.controller.entity.Match;
import com.mycompany.tennis.controller.repository.MatchRepositoryImpl;
import com.mycompany.tennis.controller.repository.ScoreRepositoryImpl;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
  /*
  //Utiliser Dao à la place des deux repository
    private MatchDaoImpl matchDao;
*/
    public MatchService(){
            this.scoreRepository = new ScoreRepositoryImpl();
            this.matchRepository = new MatchRepositoryImpl();
        /*
        //Utiliser Dao à la place des deux repository. On l'intencie
        matchDao = new MatchDaoImpl();
        */
    }

    public void enregistrerNouveauMatch(Match match){
            matchRepository.create(match);
            scoreRepository.create(match.getScore());
       /*    //Utilisant Dao
        matchDao.createMatchWithScore(match);*/
    }
}
