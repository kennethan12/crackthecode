/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Round model. Contains the round id, the Game of that round, the user's guess,
 * the number of exact matches (when a number and its position is correct), the
 * number of partial matches (when a number is correct), and the time when the
 * guess was made.
 *
 * @author kennethan
 */
public class Round {

    private int id;
    private Game game;
    private String guess;
    private int exactMatch;
    private int partialMatch;
    private LocalDateTime time;

    public Round() {
        this.exactMatch = 0;
        this.partialMatch = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getExactMatch() {
        return exactMatch;
    }

    public void setExactMatch(int exactMatch) {
        this.exactMatch = exactMatch;
    }

    public int getPartialMatch() {
        return partialMatch;
    }

    public void setPartialMatch(int partialMatch) {
        this.partialMatch = partialMatch;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.game);
        hash = 89 * hash + Objects.hashCode(this.guess);
        hash = 89 * hash + this.exactMatch;
        hash = 89 * hash + this.partialMatch;
        hash = 89 * hash + Objects.hashCode(this.time);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.exactMatch != other.exactMatch) {
            return false;
        }
        if (this.partialMatch != other.partialMatch) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

}
