/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.data;

import com.ka.crackthecode.models.Game;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kennethan
 */
public class GameDatabaseDaoStub implements GameDao {

    private List<Game> games = new ArrayList<Game>();
    int nextId;

    public GameDatabaseDaoStub() {
        nextId = 1;
    }

    @Override
    public Game add(Game game) {
        game.setId(nextId);
        games.add(game);
        nextId++;
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return games;
    }

    @Override
    public Game getById(int id) {
        for (Game game : games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }

    @Override
    public boolean update(Game game) {
        game.setIsFinished(true);
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        for (Game game : games) {
            if (game.getId() == id) {
                games.remove(game);
                return true;
            }
        }
        return false;
    }

}
