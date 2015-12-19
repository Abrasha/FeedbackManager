package sample.entity.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import sample.Main;
import sample.entity.Feedback;
import sample.helpers.HibernateUtil;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by abrasha on 12/16/15.
 */
public class FeedbackDAO extends AbstractDAO<Feedback> {

    private static SessionFactory factory;
    private static FeedbackDAO dao;



    private FeedbackDAO(){
        factory = HibernateUtil.getSessionFactory();
    }



    public static FeedbackDAO getInstance(){
        if (dao == null){
            dao = new FeedbackDAO();
        }
        return dao;
    }



    @Override
    public List<Feedback> getAll(){
        startTransaction();
        Main.logger.log(Level.INFO, "Getting full list of feedback");
        Query query = session.createQuery("from Feedback");
        List<Feedback> result = query.list();
        endTransaction();
        return result;
    }



    @Override
    public Feedback get(int id){
        startTransaction();
        Main.logger.log(Level.INFO, "Getting feedback with id: " + id);
        Feedback result = session.get(Feedback.class, id);
        endTransaction();
        return result;
    }



    @Override
    public void delete(int id){
        startTransaction();
        Main.logger.log(Level.INFO, "Removing feedback with id: " + id);
        Feedback toDelete = get(id);
        session.delete(toDelete);
        endTransaction();
    }
}
