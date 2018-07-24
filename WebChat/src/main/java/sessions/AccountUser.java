package sessions;

import chat.ChatWebSocket;
import dbService.dataSets.UsersDataSet;

import javax.servlet.http.HttpSession;

public class AccountUser {

    private UsersDataSet usersDataSet;
    private ChatWebSocket webSocket;
    private HttpSession httpSession;

    public UsersDataSet getUsersDataSet() {
        return usersDataSet;
    }

    public void setUsersDataSet(UsersDataSet usersDataSet) {
        this.usersDataSet = usersDataSet;
    }

    public ChatWebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(ChatWebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public AccountUser() {
    }
}
