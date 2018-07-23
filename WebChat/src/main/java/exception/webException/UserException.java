package exception.webException;

public class UserException extends WebException {
    public UserException(Throwable throwable) {
        super(throwable);
    }

    public UserException (String message) {
        super(message);
    }
}
