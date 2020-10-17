/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.data;

import com.ka.crackthecode.TestApplicationConfiguration;
import com.ka.crackthecode.models.Game;
import com.ka.crackthecode.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author kennethan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDatabaseDaoTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public RoundDatabaseDaoTest() {
    }

    @Before
    public void setUp() {
        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteById(round.getId());
        }

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteById(game.getId());
        }
    }

    @Test
    public void testAddGetMethod() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        game = gameDao.add(game);

        Round round = new Round();
        round.setGame(game);
        round.setGuess("1235");
        round.setTime(LocalDateTime.now().withNano(0));
        round = roundDao.addRound(round);

        Round fromDao = roundDao.getRoundById(round.getId());

        assertEquals(round, fromDao);
    }

    @Test
    public void testGetAllMethod() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        game = gameDao.add(game);

        Round round = new Round();
        round.setGame(game);
        round.setGuess("1235");
        round.setTime(LocalDateTime.now().withNano(0));
        round = roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGame(game);
        round2.setGuess("1234");
        round2.setTime(LocalDateTime.now().withNano(0));
        round2 = roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRounds();

        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));
    }

    @Test
    public void testGetRoundsForGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        game = gameDao.add(game);

        Game game2 = new Game();
        game2.setAnswer("5678");
        game2.setIsFinished(true);
        gameDao.add(game2);

        Round round = new Round();
        round.setGame(game);
        round.setGuess("1235");
        round.setTime(LocalDateTime.now().withNano(0));
        round = roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGame(game2);
        round2.setGuess("5678");
        round2.setTime(LocalDateTime.now().withNano(0));
        round2 = roundDao.addRound(round2);

        Round round3 = new Round();
        round3.setGame(game);
        round3.setGuess("1235");
        round3.setTime(LocalDateTime.now().withNano(0));
        round3 = roundDao.addRound(round3);

        List<Round> rounds = roundDao.getRoundsForGame(game);

        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round3));
        assertFalse(rounds.contains(round2));

    }

}
