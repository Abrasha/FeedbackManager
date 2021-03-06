package com.aabramov.entity.dao;

import com.aabramov.Main;
import com.aabramov.entity.Restaurant;
import com.aabramov.helpers.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.Level;

/**
 * @author Andrii Abramov on 12/16/15.
 */
public class RestaurantDAO extends AbstractDAO<Restaurant> {
    
    private static SessionFactory factory;
    private static RestaurantDAO dao;
    
    
    private RestaurantDAO() {
        factory = HibernateUtil.getSessionFactory();
    }
    
    
    public static RestaurantDAO getInstance() {
        if (dao == null) {
            dao = new RestaurantDAO();
        }
        return dao;
    }
    
    
    @Override
    public List<Restaurant> getAll() {
        startTransaction();
        Main.logger.log(Level.INFO, "Getting full list of restaurants");
        Query query = session.createQuery("from Restaurant ");
        List<Restaurant> result = query.list();
        endTransaction();
        return result;
    }
    
    
    @Override
    public Restaurant get(int id) {
        startTransaction();
        Main.logger.log(Level.INFO, "Getting restaurant with id: " + id);
        Restaurant result = session.get(Restaurant.class, id);
        endTransaction();
        return result;
    }
    
    
    @Override
    public void delete(int id) {
        startTransaction();
        Main.logger.log(Level.INFO, "Removing restaurant with id: " + id);
        Restaurant toDelete = get(id);
        session.delete(toDelete);
        endTransaction();
    }
}
