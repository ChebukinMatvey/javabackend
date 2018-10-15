package com.nokinobi.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.nokinobi.items.Filter;

public class ContentStatements {

    public static PreparedStatement GetAllItems() {
        PreparedStatement st = null;
        try {
            st = Database.GetConnection().prepareStatement(SqlStrings.GetAllItems);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public static PreparedStatement GetFilteredItems(Filter filter) {
        String query = "select * from goods where";
        if (!filter.getName().equals("default"))
            query += " name='" + filter.getName() + "'";
        else
            query += " name in('IPhone Se','IPhone 10','IPhone 6s','IPhone 7','IPhone 8')";
        if (!filter.getCapacity().equals("default"))
            query += " and capacity=" + filter.getCapacity();
        else
            query +=" and capacity in(32,64,128)";
        query += " and price>" + filter.getPriceMin() + " and price < " + filter.getPriceMax();


        PreparedStatement st = null;
        try {
            st = Database.GetConnection().prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;

    }

}
