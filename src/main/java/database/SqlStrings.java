package database;

public class SqlStrings {
    public static final String GetAllItems="select * from goods";
    public static final String GetUser="select * from users where login=? and pass=?";

    public static final String InsertUser = "insert into users(login,pass)values(?,?)";
    public static final String InsertOrder = "insert into orders(name,capacity,price,login,adress,email)values(?,?,?,?,?,?)";
}
