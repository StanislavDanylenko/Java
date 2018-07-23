package base;

import exception.DBException;
import dbService.dataSets.UsersDataSet;

public interface DBService {

    public UsersDataSet getUser(long id) throws DBException;

    public UsersDataSet getUser(String login) throws DBException;

    public long addUser(String name, String password) throws DBException;

    public void printConnectInfo();
}
