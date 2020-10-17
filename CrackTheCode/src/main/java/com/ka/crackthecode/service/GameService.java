/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.service;

import com.ka.crackthecode.models.Game;
import com.ka.crackthecode.models.Round;
import java.util.List;

/**
 * The service layer interface of the application. As it handles the business
 * logic of the application, it must start a new game, process a guess, retrieve
 * a game by its id, get a list of all games in the database, and get a list of
 * rounds of a specific game.
 *
 * @author kennethan
 */
public interface GameService {

    /**
     * Starts a new game by creating a new Game object and adding it to the
     * database. Game should be marked unfinished.
     *
     * @return the game that is added
     */
    public Game startNewGame();

    /**
     * Processes a guess by taking in the guess String and its associated game
     * id, and returns a Round object that contains the guess and the gameId, as
     * well as the numbers of exact and partial matches and the time the guess
     * was made.
     *
     * @param guess that the user makes
     * @param gameId of the game the user is playing
     * @return the Round that encapsulates the guess's information
     */
    public Round makeGuess(String guess, int gameId);

    /**
     * Get a game from the database based on a given id. If a game is
     * unfinished, the answer must be hidden for it is still going. An
     * SQLException is thrown if the id is invalid.
     *
     * @param gameId of the game being retrieved
     * @return game with that gameId, with its answers hidden if the gams not
     * finished
     */
    public Game getGame(int gameId);

    /**
     * Get a list of all the games in the database. If some of the games are not
     * finished, the answers are hidden.
     *
     * @return a list of games in the database, with answers hidden if the games
     * are not finished. The list is empty if there are no existing games
     */
    public List<Game> allGames();

    /**
     * Get a list of the rounds of a game with the passed in gameId. The list is
     * empty if no rounds have been played. SQLException thrown if the gameId is
     * invalid.
     *
     * @param gameId of the game of which the rounds are played for
     * @return a list of rounds, empty if no rounds have been played
     */
    public List<Round> allRoundsOfGame(int gameId);
}
