package com.nokinobi.items;


import com.nokinobi.repository.Database;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name= Database.Tables.Orders)
public class Order {
    @Column(name = Database.OrdersFields.Id)
    @Id
    @GeneratedValue
    private int id;
    @Column(name = Database.OrdersFields.Email)
    private String emal;
    @Column(name = Database.OrdersFields.Adr)
    private String adr;

    @ManyToOne
    @JoinColumn(name=Database.OrdersFields.UserId)
    private User user_id;

    @JoinTable(
            name = "order_iphone",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "good_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<IPhone> good_id;

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public void setGood_id(Collection<IPhone> items) {
        this.good_id = items;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public User getUser_id() {
        return user_id;
    }

    public Collection<IPhone> getGood_id() {
        return good_id;
    }

    public String getEmal() {
        return emal;
    }

    public String getAdr() {
        return adr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}