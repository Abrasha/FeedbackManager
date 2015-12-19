package sample.entity.dao;

import org.hibernate.Query;
import sample.Main;
import sample.entity.Address;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by abrasha on 12/16/15.
 */
public class AddressDAO extends AbstractDAO<Address> {

    private static AddressDAO dao;

    public static AddressDAO getInstance(){
        if (dao == null){
            dao = new AddressDAO();
        }
        return dao;
    }

    @Override
    public List<Address> getAll(){
        startTransaction();
        Main.logger.log(Level.INFO, "Getting full list of addresses");
        Query query = session.createQuery("from Address");
        List<Address> result = query.list();
        endTransaction();
        return result;
    }



    @Override
    public Address get(int id){
        startTransaction();
        Main.logger.log(Level.INFO, "Getting address with id: " + id);
        Address result = session.get(Address.class, id);
        endTransaction();
        return result;
    }



    @Override
    public void delete(int id){
        startTransaction();
        Main.logger.log(Level.INFO, "Removing address with id: " + id);
        Address toDelete = get(id);
        session.delete(toDelete);
        endTransaction();
    }
}
