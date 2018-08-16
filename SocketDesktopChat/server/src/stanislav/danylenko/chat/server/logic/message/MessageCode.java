package stanislav.danylenko.chat.server.logic.message;

public class MessageCode {
    public static int OK = 200;
    public static int REGISTERED = 201;
    public static int AUTHORIZIED = 202;
    public static int SENDED = 203;
    public static int CONNECTION_RESET = 204;

    public static int CONNECTION_ERROR = 400;
    public static int LOGIN_ACCESS_ERROR = 401;
    public static int AUTHORIZATION_ERROR = 402;
    public static int REGISTRATION_ERROR = 403;
    public static int USER_NOT_FOND = 404;
    public static int LOG_OUT_ERROR = 405;
    public static int INVALID_AUTH_DATA = 406;
    public static int INVALID_REGISTER_DATA = 407;
    public static int REGISTRATION_WRONG_DATA = 408;

    public static int SERVER_ERROR = 500;
}
