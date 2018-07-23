package sessions;

import base.Account;
import dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountService implements Account {
    private final Map<String, UsersDataSet> sessionIdToProfile;

    public AccountService() {
        sessionIdToProfile = new HashMap<>();
    }

    public void addSession(String sessionId, UsersDataSet userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

    @Override
    public int getUserCount() {
        return sessionIdToProfile.size();
    }
}
