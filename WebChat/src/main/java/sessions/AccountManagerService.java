package sessions;

import chat.ChatWebSocket;
import dbService.dataSets.UsersDataSet;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AccountManagerService implements AccountManager {
    private final Map<String, AccountUser> userLoginToProfile;

    public AccountManagerService() {
        userLoginToProfile = new HashMap<>();
    }

    public void addUser(String login) {
        addUser(login, new AccountUser());
    }

    public void addUser(String login, AccountUser accountUser) {
        userLoginToProfile.put(login, accountUser);
    }

    public void addHttpSession(String login, HttpSession sessionId) {
        AccountUser tempAccountUser = userLoginToProfile.get(login);
        tempAccountUser.setHttpSession(sessionId);
        userLoginToProfile.put(login, tempAccountUser);
    }

    public void addSocketSession(String login, ChatWebSocket webSocket) {
        AccountUser tempAccountUser = userLoginToProfile.get(login);
        tempAccountUser.setWebSocket(webSocket);
        userLoginToProfile.put(login, tempAccountUser);
    }

    public void addUserDataSet(String login, UsersDataSet dataSet) {
        AccountUser tempAccountUser = userLoginToProfile.get(login);
        tempAccountUser.setUsersDataSet(dataSet);
        userLoginToProfile.put(login, tempAccountUser);
    }

    public HttpSession getHttpSession(String login) {
        return (userLoginToProfile.get(login) != null)? userLoginToProfile.get(login).getHttpSession() : null;
    }

    public ChatWebSocket getSocketSession(String login) {
        return (userLoginToProfile.get(login) != null)? userLoginToProfile.get(login).getWebSocket() : null;
    }

    public UsersDataSet getUserDataSet(String login) {
        return (userLoginToProfile.get(login) != null)? userLoginToProfile.get(login).getUsersDataSet() : null;
    }

    public void deleteUser(String login) {
        AccountUser tempAccountUser = userLoginToProfile.get(login);
        if (tempAccountUser != null) {
            tempAccountUser.setUsersDataSet(null);
            tempAccountUser.setHttpSession(null);
            tempAccountUser.setWebSocket(null);
            userLoginToProfile.remove(login);
        }
    }

    public Set<String> getUsersLoginList() {
        return userLoginToProfile.keySet();
    }

    public Set<HttpSession> getHttpSessoinList() {
        Set<HttpSession> httpSessions = new HashSet<>();
        for (String login : getUsersLoginList()) {
            httpSessions.add(userLoginToProfile.get(login).getHttpSession());
        }
        return httpSessions;
    }

    public Set<ChatWebSocket> getWebSocketServletList() {
        Set<ChatWebSocket> webSockets = new HashSet<>();
        for (String login : getUsersLoginList()) {
            webSockets.add(userLoginToProfile.get(login).getWebSocket());
        }
        return webSockets;
    }

    public Set<UsersDataSet> getUserDatasetList() {
        Set<UsersDataSet> dataSets = new HashSet<>();
        for (String login : getUsersLoginList()) {
            dataSets.add(userLoginToProfile.get(login).getUsersDataSet());
        }
        return dataSets;
    }

    public int getUserCount() {
        return userLoginToProfile.size();
    }
}
