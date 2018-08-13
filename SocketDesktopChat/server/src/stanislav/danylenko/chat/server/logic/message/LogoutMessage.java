package stanislav.danylenko.chat.server.logic.message;

public class LogoutMessage extends AuthorizationMessage {

    public LogoutMessage(String login, String password) {
        super(login, password);
        super.operation = "logout";
    }
}
