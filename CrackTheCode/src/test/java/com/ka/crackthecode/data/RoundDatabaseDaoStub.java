/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.data;

import com.ka.crackthecode.models.Game;
import com.ka.crackthecode.models.Round;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kennethan
 */
public class RoundDatabaseDaoStub implements RoundDao {

    private List<Round> rounds = new ArrayList<>();
    int nextId;

    public RoundDatabaseDaoStub() {
        nextId = 1;
    }

    @Override
    public List<Round> getAllRounds() {
        return rounds;
    }

    @Override
    public Round getRoundById(int id) {
        for (Round round : rounds) {
            if (round.getId() == id) {
                return round;
            }
        }
        return null;
    }

    @Override
    public Round addRound(Round round) {
        round.setId(nextId);
        nextId++;
        rounds.add(round);
        return round;
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        List<Round> roundsOfGame = new ArrayList<>();
        for (Round round : rounds) {
            if (round.getGame().getId() == game.getId()) {
                roundsOfGame.add(round);
            }
        }
        return roundsOfGame;
    }

    @Override
    public void deleteById(int id) {
        for (Round round : rounds) {
            if (round.getId() == id) {
                rounds.remove(id);
            }
        }
    }

}
