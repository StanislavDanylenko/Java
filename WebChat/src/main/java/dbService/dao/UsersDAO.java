package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public UsersDataSet getUserByLogin(String name) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteriaQuery = builder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> root = criteriaQuery.from(UsersDataSet.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("login"), name));
        Query<UsersDataSet> q = session.createQuery(criteriaQuery);
        List<UsersDataSet> result = q.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name));
    }

    public long insertUser(String name, String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name, password));
    }
}
