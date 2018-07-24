package dbService;

import base.DBService;
import dbService.dataSets.UsersDataSet;
import exception.DBException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class DBServiceImpl implements DBService {
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String HIBERNATE_HBM2DDL_AUTO = "create";

    private final SessionFactory sessionFactory;

    public DBServiceImpl() {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        return configuration;
    }

    public UsersDataSet getUser(long id) throws DBException {
        TSessionQueryExecutor tSessionQueryExecutor = new TSessionQueryExecutor();
        return tSessionQueryExecutor.getQuery(sessionFactory, dao -> dao.get(id));
    }

    public UsersDataSet getUser(String login) throws DBException {
        TSessionQueryExecutor tSessionQueryExecutor = new TSessionQueryExecutor();
        return tSessionQueryExecutor.getQuery(sessionFactory, dao -> dao.getUserByLogin(login));
    }

    public long addUser(String name, String password) throws DBException {
        TSessionQueryExecutor tSessionQueryExecutor = new TSessionQueryExecutor();
        return tSessionQueryExecutor.changeQuery(sessionFactory, dao -> dao.insertUser(name, password));
    }

    public void printConnectInfo() {
        Session session = sessionFactory.openSession();
        session.doWork(connection1 -> {
            System.out.println("DB name: " + connection1.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection1.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection1.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection1.getAutoCommit());
        });
        session.close();
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
