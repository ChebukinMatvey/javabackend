package Database;

import java.sql.*;

public class DatabaseOperation<T> {

    public static <T> T  ExecuteQuery(PreparedStatement st, IResultHandler<T> handler){
        ResultSet set = null;
        try {
            set=st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // close connection in handler
        return handler.handle(set);
    }

    public static int ExecuteUpdate(PreparedStatement preparedStatement) {
        int res=0;
        Connection connection = null;
        try {
            connection=preparedStatement.getConnection();
            connection.setAutoCommit(false);
            res=preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            if(connection!=null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return res;
    }


    public static int ExecuteBatch(PreparedStatement preparedStatement) {
        int[] res=null;
        Connection connection = null;
        try {
            connection=preparedStatement.getConnection();
            connection.setAutoCommit(false);
            res=preparedStatement.executeBatch();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            if(connection!=null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        int sum=0;
        for (int i:res) {
            sum+=i;
        }
        return sum;
    }
}
