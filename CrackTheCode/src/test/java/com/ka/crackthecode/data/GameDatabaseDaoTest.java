/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.data;

import com.ka.crackthecode.TestApplicationConfiguration;
import com.ka.crackthecode.models.Game;
import com.ka.crackthecode.models.Round;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
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
public class GameDatabaseDaoTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public GameDatabaseDaoTest() {
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

        Game fromDao = gameDao.getById(game.getId());
        assertEquals(game, fromDao);
    }

    @Test
    public void testGetAllMethod() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDao.add(game);

        Game game2 = new Game();
        game2.setAnswer("5678");
        game2.setIsFinished(true);
        gameDao.add(game2);

        Game game3 = new Game();
        game3.setAnswer("1357");
        game3.setIsFinished(true);
        gameDao.add(game3);

        List<Game> games = gameDao.getAllGames();
        assertEquals(3, games.size(), "There should be 3 games.");

        // First game is not finished, so answer must be hidden.
        assertFalse(games.contains(game), "Answer is hidden, so not equal");
        Game hiddenGame = null;
        for (Game g : games) {
            if (g.getId() == game.getId()) {
                hiddenGame = g;
            }
        }
        assertNotNull(hiddenGame, "First game should be hidden.");
        assertEquals("xxxx", hiddenGame.getAnswer(), "Answer should be hidden.");

        // Other two games are finished.
        assertTrue(games.contains(game2));
        assertTrue(games.contains(game3));
    }

    @Test
    public void testUpdateMethod() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setIsFinished(false);
        game = gameDao.add(game);

        Game fromDao = gameDao.getById(game.getId());
        assertEquals(fromDao, game);

        game.setIsFinished(true);
        gameDao.update(game);
        assertNotEquals(fromDao, game);

        fromDao = gameDao.getById(game.getId());
        assertEquals(fromDao, game);
    }

}
