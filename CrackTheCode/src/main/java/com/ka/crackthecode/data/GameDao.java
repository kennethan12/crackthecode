/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.data;

import com.ka.crackthecode.models.Game;
import java.util.List;

/**
 * The Game DAO. Able to add a Game, get all the Games, get a Game by its id,
 * update a Game object, and delete a Game by its id.
 *
 * @author kennethan
 */
public interface GameDao {

    /**
     * Add a Game to the database. Return null when a Game contains incorrect
     * data.
     *
     * @param game that is being added
     * @return the game added, null if the game could not be added
     */
    Game add(Game game);

    /**
     * Get all the games in the database. If a game is not finished, hide the
     * answer.
     *
     * @return a list of Games with their answers hidden if they are not
     * finished
     */
    List<Game> getAllGames();

    /**
     * Get a game by its id. An SQLException is thrown if the id does not exist
     * for a game.
     *
     * @param id of the game
     * @return game with that id
     */
    Game getById(int id);

    /**
     * Update a game so that it is marked finished.
     *
     * @param game that is being updated
     * @return true if the game was updated, false if not found
     */
    boolean update(Game game);

    /**
     * Delete a game by its id. This application does not use this function and
     * is only for testing purposes.
     *
     * @param id of the game being deleted
     * @return true if the game was deleted, false if not found
     */
    boolean deleteById(int id);
}
