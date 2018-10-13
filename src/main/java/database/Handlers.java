package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import items.*;

public class Handlers<T> {

    public static <T> IResultHandler<T> getAllItemsHandler() {
        return new IResultHandler<T>() {
            public T handle(ResultSet set) {
                Connection con = null;
                try {
                    con = set.getStatement().getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                List<IPhone> items = new ArrayList<IPhone>();
                try {
                    while (set.next()) {
                        items.add(new IPhone(set.getString("name"),
                                set.getString("imgStr"),
                                set.getInt("price"),
                                set.getInt("capacity")));
                    }
                } catch (SQLException e) {}

                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return (T) items;
            }
        };
    }

    public static <T> IResultHandler<T> FindUserHandler(){
        return  new IResultHandler<T>() {
            public T handle(ResultSet set) {
                Connection con = null;
                Boolean res = null;
                try {
                    con=set.getStatement().getConnection();
                    res= set.next();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return (T)res;
            }
        };
    }

}
