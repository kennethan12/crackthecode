///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ka.crackthecode.service;
//
//import com.ka.crackthecode.TestApplicationConfiguration;
//import com.ka.crackthecode.data.GameDao;
//import com.ka.crackthecode.data.GameDatabaseDaoStub;
//import com.ka.crackthecode.data.RoundDao;
//import com.ka.crackthecode.data.RoundDatabaseDaoStub;
//import com.ka.crackthecode.models.Game;
//import com.ka.crackthecode.models.Round;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// *
// * @author kennethan
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestApplicationConfiguration.class)
//public class GameServiceImplTest {
//
//    private GameDao gameDao;
//    private RoundDao roundDao;
//    private GameService service;
//
//    public GameServiceImplTest() {
//        gameDao = new GameDatabaseDaoStub();
//        roundDao = new RoundDatabaseDaoStub();
//        this.service = new GameServiceImpl();
//    }
//
//    @Before
//    public void setUp() {
//        List<Game> games = gameDao.getAllGames();
//        for (Game game : games) {
//            gameDao.deleteById(game.getId());
//        }
//
//        List<Round> rounds = roundDao.getAllRounds();
//        for (Round round : rounds) {
//            roundDao.deleteById(round.getId());
//        }
//    }
//
//    @Test
//    public void testStartNewGame() {
//        Game game = service.startNewGame();
//
//        assertNotNull(game);
//    }
//
//}
