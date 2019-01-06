package com.nokinobi.repository;

public class Database {
    public static class Tables{
        public static final String Goods="goods";
        public static final String Users="users";
        public static final String Orders="orders";
    }
    public static class OrdersFields{
        public static final String Id="id";
        public static final String UserId="user_id";
        public static final String GoodId ="good_id";
        public static final String Adr="adr";
        public static final String Email="email";
    }
    public class GoodsFields {
        public static final String Name = "name";
        public static final String ImgStr ="imgStr" ;
        public static final String Price = "price";
        public static final String Capacity = "capacity";
        public static final String Id="id";
    }
    public class UsersFileds {
        public static final String Id ="id" ;
        public static final String Login="login";
        public static final String Pass="pass";
    }
}
