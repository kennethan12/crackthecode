/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.models;

/**
 * This model is only used for when a guess is being made. Requires a guess made
 * by the user as well as the gameId of the game they are guessing for.
 *
 * @author kennethan
 */
public class EnterGuess {

    private String guess;
    private int gameId;

    public String getGuess() {
        return guess;
    }

    public int getGameId() {
        return gameId;
    }

}
