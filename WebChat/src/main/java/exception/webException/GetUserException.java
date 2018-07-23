package exception.webException;

public class GetUserException extends UserException {
    public GetUserException(Throwable throwable) {
        super(throwable);
    }

    public GetUserException(String message) {
        super(message);
    }
}
