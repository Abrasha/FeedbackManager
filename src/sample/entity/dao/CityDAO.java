package sample.entity.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import sample.Main;
import sample.entity.City;
import sample.helpers.HibernateUtil;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by abrasha on 12/16/15.
 */
public class CityDAO extends AbstractDAO<City> {

    private static SessionFactory factory;
    private static CityDAO dao;



    private CityDAO(){
        factory = HibernateUtil.getSessionFactory();
    }



    public static CityDAO getInstance(){
        if (dao == null){
            dao = new CityDAO();
        }
        return dao;
    }

    @Override
    public List<City> getAll(){
        startTransaction();
        Main.logger.log(Level.INFO, "Getting full list of cities");
        Query query = session.createQuery("from City ");
        List<City> result = query.list();
        endTransaction();
        return result;
    }



    @Override
    public City get(int id){
        startTransaction();
        Main.logger.log(Level.INFO, "Removing city with id: " + id);
        City result = session.get(City.class, id);
        endTransaction();
        return result;
    }



    @Override
    public void delete(int id){
        startTransaction();
        Main.logger.log(Level.INFO, "Removing city with id: " + id);
        City toDelete = get(id);
        session.delete(toDelete);
        endTransaction();
    }
}
