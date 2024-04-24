package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    public Question getQuestionById(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId=?";
        return jdbcTemplate.queryForObject(sql,
                rs -> new Question(rs.getInt("questionId"), rs.getString("writer"), rs.getString("title"),
                        rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer")),
                id);
    }

    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO QUESTIONS(writer, title, contents, createdDate, countOfAnswer) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setObject(1, question.getWriter());
                pstmt.setObject(2, question.getTitle());
                pstmt.setObject(3, question.getContents());
                pstmt.setObject(4, question.getCreatedDate());
                pstmt.setObject(5, question.getCountOfAnswer());
            }
        });
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM QUESTIONS";
        return jdbcTemplate.query(sql, rs -> new Question(rs.getInt("questionId"), rs.getString("writer"), rs.getString("title"),
                rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer")));
    }

    public void delete(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "DELETE FROM QUESTIONS WHERE questionId=?";
        jdbcTemplate.update(sql, id);
    }

    public void updateContent(int id, String contents) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE QUESTIONS set contents = ? WHERE questionId = ?";
        jdbcTemplate.update(sql, contents, id);
    }

    public void updateCountOfAnswer(int id, int countOfAnswer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE QUESTIONS set countOfAnswer = ? WHERE questionId = ?";
        jdbcTemplate.update(sql, countOfAnswer, id);
    }
}
