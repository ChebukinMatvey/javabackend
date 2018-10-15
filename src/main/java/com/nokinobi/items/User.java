package com.nokinobi.items;

public class User {

    private String login;
    private String pass;

    public User() {
    }

    public User(String login, String pass) {

        this.login = login;
        this.pass = pass;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {

        return pass;
    }

    public String getLogin() {
        return login;
    }
    
    @Override
    public String toString() {
    	return "User "+getLogin()+" "+getPass();
    }
}
