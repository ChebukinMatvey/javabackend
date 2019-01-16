package com.nokinobi.items;


import com.nokinobi.repository.Database;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = Database.Tables.Users)
public class User {

    @Column(name = Database.UsersFileds.Id)
    @Id
    @GeneratedValue
    private int id;
    @Column(name = Database.UsersFileds.Login,unique = true)
    private String login;
    @Column(name=Database.UsersFileds.Pass)
    private String pass;

    @OneToMany(mappedBy = "user")
    private Collection<Order> orders;

    public User() {
    }

    public User(int id,String login, String pass) {
        this.id=id;
        this.login = login;
        this.pass = pass;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
