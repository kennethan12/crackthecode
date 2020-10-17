/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.data;

import com.ka.crackthecode.data.GameDatabaseDao.GameMapper;
import com.ka.crackthecode.models.Game;
import com.ka.crackthecode.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 * An implementation of the Round DAO using an SQL database.
 *
 * @author kennethan
 */
@Repository
public class RoundDatabaseDao implements RoundDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public RoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Round> getAllRounds() {
        final String sql = "SELECT * FROM Round";
        List<Round> rounds = jdbcTemplate.query(sql, new RoundMapper());

        rounds.forEach(round -> {
            round.setGame(getGameForRound(round));
        });
        return rounds;
    }

    @Override
    public Round getRoundById(int id) {
        try {
            final String sql = "SELECT * FROM Round WHERE roundId = ?";
            Round round = jdbcTemplate.queryForObject(sql, new RoundMapper(), id);
            round.setGame(getGameForRound(round));
            return round;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO Round(gameId, guess, exactMatch, partialMatch, time) "
                + "VALUES (?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, round.getGame().getId());
            statement.setString(2, round.getGuess());
            statement.setInt(3, round.getExactMatch());
            statement.setInt(4, round.getPartialMatch());
            statement.setTimestamp(5, Timestamp.valueOf(round.getTime()));
            return statement;
        }, keyHolder);

        round.setId(keyHolder.getKey().intValue());
        return round;
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        final String sql = "SELECT * FROM Round WHERE gameId = ?";
        List<Round> rounds = jdbcTemplate.query(sql, new RoundMapper(), game.getId());
        rounds.forEach(round -> {
            round.setGame(getGameForRound(round));
        });
        return rounds;
    }

    @Override
    public void deleteById(int id) {
        final String sql = "DELETE FROM Round WHERE roundId = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * A helper method that identifies the game of the passed in round.
     *
     * @param round that is associated with the game its being played for
     * @return the game of that round
     */
    private Game getGameForRound(Round round) {
        final String sql = "SELECT * FROM Round r JOIN Game g ON r.gameId = g.gameId WHERE r.roundId = ?";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), round.getId());
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("roundId"));
            round.setGuess(rs.getString("guess"));
            round.setExactMatch(rs.getInt("exactMatch"));
            round.setPartialMatch(rs.getInt("partialMatch"));
            round.setTime(rs.getTimestamp("time").toLocalDateTime());
            return round;
        }

    }

}
