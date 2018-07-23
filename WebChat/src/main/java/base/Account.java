package base;

import dbService.dataSets.UsersDataSet;

public interface Account {

    public void addSession(String sessionId, UsersDataSet userProfile);
    public void deleteSession(String sessionId);
    public int getUserCount();
}
