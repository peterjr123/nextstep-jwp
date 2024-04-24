package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao {
    public List<Answer> findAnswer(int questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE questionId=?";
        return jdbcTemplate.query(sql, rs -> new Answer(rs.getInt("answerId"), rs.getString("writer"), rs.getString("contents"),
                rs.getDate("createdDate"), rs.getInt("questionId"))
                , questionId);
    }

    public void insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS(writer, contents, createdDate, questionId) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
    }

    public void delete(int answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "DELETE FROM ANSWERS WHERE answerId=?";
        jdbcTemplate.update(sql, answerId);
    }

    public void update(int answerId, String contents) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE ANSWERS set contents = ? WHERE answerId = ?";
        jdbcTemplate.update(sql, contents, answerId);
    }
}
