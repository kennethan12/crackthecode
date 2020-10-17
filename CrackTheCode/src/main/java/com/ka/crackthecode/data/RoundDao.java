/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.data;

import com.ka.crackthecode.models.Game;
import com.ka.crackthecode.models.Round;
import java.util.List;

/**
 * The Round DAO. Retrieve all rounds, get a round by its id, add a round, get
 * the rounds of a specific game, and delete a round by its id.
 *
 * @author kennethan
 */
public interface RoundDao {

    /**
     * Get all the rounds in the database. Return an empty list if no rounds
     * exist.
     *
     * @return a list of rounds, could be empty
     */
    public List<Round> getAllRounds();

    /**
     * Get a round by its id. Returns an SQL Exception if the id is invalid.
     *
     * @param id of the round
     * @return the round with that id
     */
    public Round getRoundById(int id);

    /**
     * Add a round to the database.
     *
     * @param round that is being added
     * @return the round added
     */
    public Round addRound(Round round);

    /**
     * Get a list of rounds for a specific game. Return an empty list if no
     * rounds exist. SQLException thrown if game is not identified in the DB.
     *
     * @param game of which the rounds are played for
     * @return a list of rounds from that game
     */
    public List<Round> getRoundsForGame(Game game);

    /**
     * Delete a round by its id. This function is not used by this application
     * and is only used for testing purposes. Returns an SQLException if id is
     * invalid.
     *
     * @param id of the round being deleted
     */
    public void deleteById(int id);

}
