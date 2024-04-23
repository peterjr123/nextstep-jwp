package next.libs;

import core.jdbc.ConnectionManager;
import next.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCFacadeTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }



    @Test
    public void testSelectQuery() {
        User expected = new User("admin", "password", "name", "javajigi@email.com");

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        JDBCFacade selectQuery = new JDBCFacade(sql);
        selectQuery.setQueryParameter(1, expected.getUserId());
        User result = (User) selectQuery.executeQuery(new QueryResultHandler<User>() {
            @Override
            protected User handleResult(ResultSet rs) throws SQLException {
                if(rs.next())
                    return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
                return null;
            }
        });
        Assert.assertEquals(expected.getPassword(), result.getPassword());
    }

    @Test
    public void testInsertQuery() {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        JDBCFacade insertQuery = new JDBCFacade(sql);
        insertQuery.setQueryParameter(1, expected.getUserId());
        insertQuery.setQueryParameter(2, expected.getPassword());
        insertQuery.setQueryParameter(3, expected.getName());
        insertQuery.setQueryParameter(4, expected.getEmail());
        insertQuery.executeUpdate();

        String selectSql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        JDBCFacade selectQuery = new JDBCFacade(selectSql);
        selectQuery.setQueryParameter(1, expected.getUserId());
        User result = (User) selectQuery.executeQuery(new QueryResultHandler<User>() {
            @Override
            protected User handleResult(ResultSet rs) throws SQLException {
                if(rs.next())
                    return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
                return null;
            }
        });
        Assert.assertEquals(expected.getPassword(), result.getPassword());
    }
}
