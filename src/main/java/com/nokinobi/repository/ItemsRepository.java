package com.nokinobi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nokinobi.items.Filter;
import com.nokinobi.items.IPhone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class ItemsRepository {

    private static final String SelectAll = "select g FROM IPhone g";
    private static final String GetId="select g.id from IPhone g where g.name=:name and g.price=:price and g.capacity=:capacity";
    private static final String GetById="select g from IPhone g where g.id=:id";

    @PersistenceContext
    private EntityManager entityManager;

    public List<IPhone> getItems() {
        Query query = entityManager.createQuery(SelectAll);
        return query.getResultList();
    }

    public List<IPhone> getItems(Filter filter) {
        // TODO add sql from filter
        String sql=null;
        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }

    public int getId(IPhone p) {
        TypedQuery<Integer> query = entityManager.createQuery(GetId, Integer.class);
        query.setParameter("name",p.getName());
        query.setParameter("price",p.getPrice());
        query.setParameter("capacity",p.getCapacity());
        return query.getFirstResult();
    }

    public void addItem(IPhone item) {
        entityManager.persist(item);
        entityManager.flush();
    }

    public IPhone updateItem(IPhone item) {
        return entityManager.merge(item);
    }

    public void deleteItem(IPhone item) {
        entityManager.remove(entityManager.contains(item)? item:entityManager.merge(item));
    }

    public IPhone getItemById(int id) {
        TypedQuery<IPhone> query = entityManager.createQuery(GetById,IPhone.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }
}
