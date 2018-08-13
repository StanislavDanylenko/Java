package stanislav.danylenko.chat.server.logic.dbService;

import stanislav.danylenko.chat.server.logic.dbService.executor.Executor;

import java.sql.SQLException;

public class UserDAO {

    public UserDataSet getUser(long id) throws SQLException {
        Executor executor = new Executor();
        return executor.execQuery("SELECT * FROM users WHERE id = " + id, result -> {
            result.next();
            return new UserDataSet(result.getLong(1), result.getString(2),
                    result.getString(3), result.getString(4));
        });
    }

    public UserDataSet getUserByLogin(String login) throws SQLException {
        Executor executor = new Executor();
        return executor.execQuery("SELECT * FROM users WHERE login='" + login + "'", result -> {
            if(result.next()) {
                return new UserDataSet(result.getLong(1), result.getString(2),
                        result.getString(3), result.getString(4));
            } return null;
        });
    }

    public void insertUser(UserDataSet user) throws SQLException {
        Executor executor = new Executor();
        executor.execUpdate("INSERT INTO users VALUES(default, '" + user.getLogin() + "', '"
                + user.getPassword() + "', '" + user.getEmail() + "')");
    }

    public void deleteUser(UserDataSet user) throws SQLException {
        Executor executor = new Executor();
        executor.execUpdate("DELETE FROM users WHERE id=" + user.getId());
    }

    public void createTable() throws SQLException {
        Executor executor = new Executor();
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users (id INT auto_increment, login VARCHAR(25), " +
                "password VARCHAR(25), email VARCHAR(100), primary key (id))");
    }

    public void dropTable() throws SQLException {
        Executor executor = new Executor();
        executor.execUpdate("DROP TABLE users IF EXISTS");
    }

}
