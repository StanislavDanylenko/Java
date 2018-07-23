package exception.webException;

public class SuchUserAlreadyExistException extends UserException {
    public SuchUserAlreadyExistException(Throwable throwable) {
        super(throwable);
    }

    public SuchUserAlreadyExistException (String message) {
        super(message);
    }
}
