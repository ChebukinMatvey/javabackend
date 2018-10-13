package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import items.*;

public  class OrderStatement {
    public static PreparedStatement InsertOrder(Cart cart, User user, String adr, String email)
    {
        PreparedStatement st=null;
        try {
            st=Database.GetConnection().prepareStatement(SqlStrings.InsertOrder);
            for (IPhone p:cart) {
                st.setString(1,p.getName());
                st.setInt(2,p.getCapacity());
                st.setInt(3,p.getPrice());
                st.setString(4,user.getLogin());
                st.setString(5,adr);
                st.setString(6,email);
                st.addBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }
}
