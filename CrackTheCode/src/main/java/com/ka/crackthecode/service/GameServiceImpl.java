/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.service;

import com.ka.crackthecode.data.GameDao;
import com.ka.crackthecode.data.RoundDao;
import com.ka.crackthecode.models.Game;
import com.ka.crackthecode.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kennethan
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    @Override
    public Game startNewGame() {
        Game newGame = new Game();
        newGame.setAnswer(makeRandomAnswer());
        return gameDao.add(newGame);
    }

    @Override
    public Round makeGuess(String guess, int gameId) {
        if (!guess.matches("[0-9]+") || guess.length() != 4) {
            throw new IllegalArgumentException();
        }

        Game game = gameDao.getById(gameId);

        if (game.getIsFinished()) {
            return null;
        } else {
            Round round = new Round();
            round.setGuess(guess);

            int[] matches = findMatches(guess, game.getAnswer());
            round.setExactMatch(matches[0]);
            round.setPartialMatch(matches[1]);

            round.setTime(LocalDateTime.now().withNano(0));

            if (round.getExactMatch() == 4 && round.getPartialMatch() == 0) {
                gameDao.update(game);
                game = gameDao.getById(gameId);
            } else {
                game.setAnswer("xxxx");
            }

            round.setGame(game);
            return roundDao.addRound(round);
        }
    }

    @Override
    public Game getGame(int gameId) {
        Game game = gameDao.getById(gameId);
        if (!game.getIsFinished()) {
            game.setAnswer("xxxx");
        }
        return game;
    }

    @Override
    public List<Game> allGames() {
        return gameDao.getAllGames();
    }

    @Override
    public List<Round> allRoundsOfGame(int gameId) {
        Game game = gameDao.getById(gameId);
        if (game == null) {
            return new ArrayList<>();
        }
        return roundDao.getRoundsForGame(game);
    }

    /**
     * Helper method that creates a random four-digit answer, with no
     * duplicates. The answer also does not have 0 as its starting digit.
     *
     * @return String answer for a new game
     */
    private String makeRandomAnswer() {
        char[] values = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = values.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * i);
            char temp = values[i];
            values[i] = values[rand];
            values[rand] = temp;
        }

        int start = 0;
        if (values[0] == '0') {
            start++;
        }
        return String.valueOf(values).substring(start, start + 4);
    }

    /**
     * A helper method that determines how many matches are exact and how many
     * are partial. A match is exact if the number being examined is contained
     * in the answer and in the correct position. A match is partial if only the
     * former is true.
     *
     * If the guess contains duplicates, it will still count each duplicate as
     * its own digit, and will still determine matches. For example, if the
     * answer for a game is "1234" and the passed in guess is "1111", the number
     * of exact matches is 1 and the partial matches are 3.
     *
     * @param guess being made for that round
     * @param answer of the game
     * @return an int array containing the number of exact matches in the first
     * index and the number of partial matches in the second index
     */
    private int[] findMatches(String guess, String answer) {
        int exact = 0;
        int partial = 0;

        for (int i = 0; i < guess.length(); i++) {
            int indexAtAnswer = answer.indexOf(guess.charAt(i));
            if (indexAtAnswer == i) {
                exact++;
            } else if (indexAtAnswer > -1) {
                partial++;
            }
        }

        int[] matches = new int[2];
        matches[0] = exact;
        matches[1] = partial;
        return matches;
    }

}
