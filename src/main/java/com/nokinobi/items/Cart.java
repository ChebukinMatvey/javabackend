package com.nokinobi.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Cart implements Iterable<IPhone> {

    private List<IPhone> data = new ArrayList<IPhone>();

    public void add(IPhone item) {
        data.add(item);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void remove(IPhone item) {
        for (IPhone t : data) {
            if (t.equals(item)) {
                data.remove(t);
                return;
            }
        }
    }

    public int TotalPrice() {
        int sum = 0;
        for (IPhone i : data) {
            sum += i.getPrice();
        }
        return sum;
    }


    public Iterator<IPhone> iterator() {
        return data.iterator();
    }

    public Collection<IPhone> getData() {
        return data;
    }
}
