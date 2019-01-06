package com.nokinobi.items;


import com.nokinobi.repository.Database;
import com.nokinobi.items.Order;
import javax.persistence.*;

@Entity
@Table(name = Database.Tables.Goods)
public class IPhone {

    @Column(name = Database.GoodsFields.Id)
    @GeneratedValue
    @Id
    private int id;
    @Column(name = Database.GoodsFields.Name)
    private String name;
    @Column(name = Database.GoodsFields.ImgStr)
    private String imgStr;
    @Column(name=Database.GoodsFields.Price)
    private int  price;
    @Column(name=Database.GoodsFields.Capacity)
    private int capacity;

    public IPhone() {
    }

    public IPhone(String name, String imgStr, int price, int capacity) {
        this.imgStr = imgStr;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
    }

    public IPhone(String name, int capacity, int price) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getImgStr() {
        return imgStr;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object obj) {
        IPhone item= (IPhone) obj;
        return this.name.equals(item.name) && this.price == item.price && this.capacity == item.capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
