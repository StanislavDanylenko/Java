package exception.webException;

public class ValidationUserException extends UserException {
    public ValidationUserException(Throwable throwable) {
        super(throwable);
    }

    public ValidationUserException(String message) {
        super(message);
    }
}
