package com.aabramov.helpers;

import com.aabramov.Main;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

/**
 * @author Andrii Abramov on 12/16/15.
 */
public class HibernateUtil {
    
    private static SessionFactory SESSION_FACTORY;
    
    public static void load() {
        Main.logger.log(Level.INFO, "Started loading applying hibernate configurations.");
        
        if (SESSION_FACTORY == null) {
            Configuration configuration = new Configuration();
            configuration.configure(HibernateUtil.class.getResource("/com/aabramov/resources/hibernate/hibernate.cfg.xml"));
            SESSION_FACTORY = configuration.buildSessionFactory();
        }
    }
    
    public static void close() {
        if (SESSION_FACTORY != null)
            SESSION_FACTORY.close();
        Main.logger.log(Level.INFO, "Hibernate configurations are dropped.");
    }
    
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
    
}