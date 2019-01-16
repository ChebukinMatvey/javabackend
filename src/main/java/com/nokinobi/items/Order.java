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
    private User user;

    @JoinTable(
            name = "order_iphone",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "phones",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<IPhone> phones;

    public void setUser(User user) {
        this.user = user;
    }

    public void setPhones(Collection<IPhone> items) {
        this.phones = items;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public User getUser() {
        return user;
    }

    public Collection<IPhone> getPhones() {
        return phones;
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


    public int  getTotal(){
        int sum=0;
        for(IPhone p : phones)
            sum +=p.getPrice();
        return sum;
    }


}