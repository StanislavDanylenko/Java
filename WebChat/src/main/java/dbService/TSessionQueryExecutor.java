package dbService;


import base.TResultHandler;
import dbService.dao.UsersDAO;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TSessionQueryExecutor {

    public <T> T getQuery(SessionFactory sessionFactory, TResultHandler<T> resultHandler) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            T result = resultHandler.handle(dao);
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }


    public <T> T changeQuery(SessionFactory sessionFactory, TResultHandler<T> resultHandler) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            T result = resultHandler.handle(dao);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new DBException(e);
        }

    }
}
