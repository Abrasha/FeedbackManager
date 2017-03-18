package com.aabramov.entity.dao;

import com.aabramov.Main;
import com.aabramov.helpers.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.logging.Level;

/**
 * @author Andrii Abramov on 12/16/15.
 */
public abstract class AbstractDAO<T extends Serializable> implements DAO<T> {
    
    protected static SessionFactory factory;
    protected static Session session;
    protected static Transaction transaction;
    
    static {
        factory = HibernateUtil.getSessionFactory();
    }
    
    protected void startTransaction() {
        Main.logger.log(Level.INFO, "Transaction started");
        session = factory.openSession();
        transaction = session.beginTransaction();
    }
    
    
    protected void endTransaction() {
        session.flush();
        transaction.commit();
        session.close();
        Main.logger.log(Level.INFO, "Transaction finished");
    }
    
    
    @Override
    public void save(T obj) {
        startTransaction();
        Main.logger.log(Level.INFO, "Persisting object");
        session.save(obj);
        endTransaction();
    }
    
    
    @Override
    public void update(T obj) {
        startTransaction();
        Main.logger.log(Level.INFO, "Updating object");
        session.saveOrUpdate(obj);
        endTransaction();
    }
    
    
    @Override
    public void delete(T obj) {
        startTransaction();
        Main.logger.log(Level.INFO, "Removing object");
        session.delete(obj);
        endTransaction();
    }
}
