package next.libs;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class QueryResultHandler<T> {
    protected abstract T handleResult(ResultSet rs) throws SQLException;
}
