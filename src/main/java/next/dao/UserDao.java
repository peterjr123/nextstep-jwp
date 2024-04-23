package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.libs.JDBCFacade;
import next.libs.QueryResultHandler;
import next.model.User;

public class UserDao {
    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        JDBCFacade jdbcFacade = new JDBCFacade(sql);
        jdbcFacade.setQueryParameter(1, user.getUserId());
        jdbcFacade.setQueryParameter(2, user.getPassword());
        jdbcFacade.setQueryParameter(3, user.getName());
        jdbcFacade.setQueryParameter(4, user.getEmail());
        jdbcFacade.executeUpdate();
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        JDBCFacade jdbcFacade = new JDBCFacade(sql);
        jdbcFacade.setQueryParameter(1, user.getPassword());
        jdbcFacade.setQueryParameter(2, user.getName());
        jdbcFacade.setQueryParameter(3, user.getEmail());
        jdbcFacade.setQueryParameter(4, user.getUserId());
        jdbcFacade.executeUpdate();
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS";
        JDBCFacade jdbcFacade = new JDBCFacade(sql);
        return jdbcFacade.executeQuery(new QueryResultHandler<List<User>>() {
            @Override
            protected List<User> handleResult(ResultSet rs) throws SQLException {
                List<User> result = new ArrayList<>();
                while(rs.next()) {
                    result.add(new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email")));
                }
                return result;
            }
        });
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        JDBCFacade jdbcFacade = new JDBCFacade(sql);
        jdbcFacade.setQueryParameter(1, userId);
        return jdbcFacade.executeQuery(new QueryResultHandler<User>() {
            @Override
            protected User handleResult(ResultSet rs) throws SQLException {
                if(rs.next()) {
                    return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                }
                return null;
            }
        });
    }
}
