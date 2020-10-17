/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ka.crackthecode.data;

import com.ka.crackthecode.models.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 * An implementation of the Game DAO using an SQL database.
 *
 * @author kennethan
 */
@Repository
public class GameDatabaseDao implements GameDao {

    private JdbcTemplate jdbcTemplate;

    public GameDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO game(answer, isFinished) VALUES (?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.getIsFinished());
            return statement;
        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT gameId, "
                + "CASE WHEN isFinished = 0 THEN 'xxxx' ELSE answer END AS 'answer', "
                + "isFinished "
                + "FROM Game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game getById(int id) {
        final String sql = "SELECT * FROM Game WHERE gameId = ?";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public boolean update(Game game) {
        final String sql = "UPDATE Game SET "
                + "isFinished = 1 "
                + "WHERE gameId = ?";
        return jdbcTemplate.update(sql, game.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String DELETE_ROUNDS = "DELETE FROM Round WHERE gameId = ?";
        jdbcTemplate.update(DELETE_ROUNDS, id);

        final String DELETE_GAME = "DELETE FROM Game WHERE gameId = ?";
        return jdbcTemplate.update(DELETE_GAME, id) > 0;
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setIsFinished(rs.getBoolean("isFinished"));
            return game;
        }

    }

}
