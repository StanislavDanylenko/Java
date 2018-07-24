package base;

import chat.ChatWebSocket;
import dbService.dataSets.UsersDataSet;
import sessions.AccountUser;

import javax.servlet.http.HttpSession;
import java.util.Set;

public interface AccountManager {

    public void addUser(String login);

    public void addUser(String login, AccountUser accountUser);

    public void addHttpSession(String login, HttpSession sessionId);

    public void addSocketSession(String login, ChatWebSocket webSocket);

    public void addUserDataSet(String login, UsersDataSet dataSet);

    public HttpSession getHttpSession(String login);

    public ChatWebSocket getSocketSession(String login);

    public UsersDataSet getUserDataSet(String login);

    public void deleteUser(String login);

    public int getUserCount();

    public Set<String> getUsersLoginList();

    public Set<HttpSession> getHttpSessoinList();

    public Set<ChatWebSocket> getWebSocketServletList();

    public Set<UsersDataSet> getUserDatasetList();
}
