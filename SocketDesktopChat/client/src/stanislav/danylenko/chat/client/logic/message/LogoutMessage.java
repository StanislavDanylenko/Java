package stanislav.danylenko.chat.client.logic.message;

public class LogoutMessage extends AuthorizationMessage {

    public LogoutMessage(String login, String password) {
        super(login, password);
        super.operation = "logout";
    }
}
