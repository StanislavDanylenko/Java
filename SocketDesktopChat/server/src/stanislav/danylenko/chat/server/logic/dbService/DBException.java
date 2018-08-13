package stanislav.danylenko.chat.server.logic.dbService;

public class DBException extends Exception{
    public DBException(Throwable throwable) {
        super(throwable);
    }

    public DBException(String message) {
        super(message);
    }
}
