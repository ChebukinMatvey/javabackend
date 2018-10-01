package Database;

import Items.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserStatements {

    public  static PreparedStatement FindUserStatement(User user)
    {
        return init(SqlStrings.GetUser,user);
    }

    public static PreparedStatement InsertUser(User user) {
        return init(SqlStrings.InsertUser,user);
    }

    private static PreparedStatement init(String query,User user){
        PreparedStatement st = null;
        try {
            st=Database.GetConnection().prepareStatement(query);
            st.setString(1,user.getLogin());
            st.setString(2,user.getPass());
        } catch (SQLException e) {


            e.printStackTrace();
        }
        return st;
    }

}
