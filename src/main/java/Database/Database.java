package Database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static DataSource ds = null;

    public static Connection GetConnection() {
        if (ds == null) {
            Context initCtx = null;
            try {
                initCtx = new InitialContext();
                ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/datasource");
            } catch (NamingException e) {
                e.printStackTrace(System.out);
            }
        }
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }


}
