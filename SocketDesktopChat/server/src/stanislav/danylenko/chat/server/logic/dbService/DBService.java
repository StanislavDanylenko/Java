package stanislav.danylenko.chat.server.logic.dbService;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLException;

public class DBService {

    public DBService() {
    }

    public static UserDataSet getUser(long id) throws DBException, SQLException {
        return (new UserDAO().getUser(id));
    }

    public static UserDataSet getUserByLogin(String login) {
        try {
            UserDAO dao = new UserDAO();
            return dao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addUser(UserDataSet user) throws DBException {
        try {
            UserDAO dao = new UserDAO();
            dao.insertUser(user);
        } catch (SQLException e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
                throw new DBException("User with this login already registered!");
            }
            throw new DBException("Registration error!");
        }
    }

    public static void cleanUp() throws DBException {
        UserDAO dao = new UserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void printConnectInfo() {
        try {
            Connection connection = getMysqlConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("desktop_messenger?").          //db name
                    append("user=root&").          //login
                    append("password=root").        //password
                    append("&useUnicode=true").
                    append("&useJDBCCompliantTimezoneShift=true").
                    append("&useLegacyDatetimeCode=false").
                    append("&serverTimezone=Europe/Moscow").
                    append("&useSSL=false").
                    append("&allowPublicKeyRetrieval=true");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException
                | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
