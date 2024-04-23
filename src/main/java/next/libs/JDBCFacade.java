package next.libs;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCFacade {
    Connection con;
    PreparedStatement preparedStatement;

    public JDBCFacade(String sql) {
        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setQueryParameter(int index, String parameter) {
        try {
            preparedStatement.setString(index, parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T executeQuery(QueryResultHandler<T> executor) {
        try {
            ResultSet rs = preparedStatement.executeQuery();
            T result = executor.handleResult(rs);
            rs.close();
            tearDown();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate() {
        try {
            preparedStatement.executeUpdate();
            tearDown();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void tearDown() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
