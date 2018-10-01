package Database;

import java.sql.ResultSet;

public interface IResultHandler<T> {
    T handle(ResultSet set);
}
