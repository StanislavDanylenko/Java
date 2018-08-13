package stanislav.danylenko.chat.server.logic.dbService.executor;

import stanislav.danylenko.chat.server.logic.dbService.DBService;

import java.sql.*;

public class Executor {

    public Executor() {}

    public void execUpdate(String update) throws SQLException {

        Connection connection = DBService.getMysqlConnection();
        connection.setAutoCommit(false);

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(update);

        connection.commit();

        stmt.close();
        connection.close();
    }

    public <T> T execQuery(String query, ResultHandler<T> handler)
            throws SQLException {

        Connection connection = DBService.getMysqlConnection();
        Statement stmt = connection.createStatement();

        stmt.executeQuery(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();

        stmt.close();
        connection.close();
        return value;
    }
}
