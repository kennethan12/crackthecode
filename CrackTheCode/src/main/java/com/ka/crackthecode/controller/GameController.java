/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.controller;

import com.ka.crackthecode.models.EnterGuess;
import com.ka.crackthecode.models.Game;
import com.ka.crackthecode.models.Round;
import com.ka.crackthecode.service.GameService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kennethan
 */
@RestController
@RequestMapping("/api/crackthecode")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    /**
     * "begin" - POST – Starts a game, generates an answer, and sets the correct
     * status. Should return a 201 CREATED message as well as the created
     * gameId.
     *
     * @return
     */
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int startGame() {
        Game game = service.startNewGame();
        return game.getId();
    }

    /**
     * "guess" – POST – Makes a guess by passing the guess and gameId in as
     * JSON. The program must calculate the results of the guess and mark the
     * game finished if the guess is correct. It returns the Round object with
     * the results filled in.
     *
     * @param enterGuess
     * @return
     */
    @PostMapping("/guess")
    public Round guess(@RequestBody EnterGuess enterGuess) {
        return service.makeGuess(enterGuess.getGuess(), enterGuess.getGameId());
    }

    /**
     * "game" – GET – Returns a list of all games. Be sure in-progress games do
     * not display their answer.
     *
     * @return
     */
    @GetMapping("/game")
    public List<Game> allGames() {
        return service.allGames();
    }

    /**
     * "game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-
     * progress games do not display their answer.
     *
     * @param gameId
     * @return
     */
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> findById(@PathVariable int gameId) {
        Game game = service.getGame(gameId);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }

    /**
     * "rounds/{gameId} – GET – Returns a list of rounds for the specified game
     * sorted by time.
     *
     * @param gameId
     * @return
     */
    @GetMapping("/rounds/{gameId}")
    public List<Round> findRoundsByGameId(@PathVariable int gameId) {
        return service.allRoundsOfGame(gameId);
    }
}
