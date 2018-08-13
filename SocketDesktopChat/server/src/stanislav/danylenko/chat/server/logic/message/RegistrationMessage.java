package stanislav.danylenko.chat.server.logic.message;

import stanislav.danylenko.chat.server.logic.dbService.UserDataSet;

public class RegistrationMessage extends AbstractMessage {
    private UserDataSet user;

    public RegistrationMessage(String login, String password, String email) {
        user = new UserDataSet(login, password, email);
        super.operation = "registration";
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }
}
