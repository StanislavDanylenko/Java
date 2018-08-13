package stanislav.danylenko.chat.server.logic.message;

import stanislav.danylenko.chat.server.logic.dbService.UserDataSet;

public class AuthorizationMessage extends AbstractMessage {
    private UserDataSet user;

    public AuthorizationMessage(String login, String password) {
        user = new UserDataSet(login, password, null);
        super.operation = "authorization";
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }
}
