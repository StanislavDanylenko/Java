package exception.webException;

public class WebException extends Exception {
    public WebException(Throwable throwable) {
        super(throwable);
    }

    public WebException(String message) {
        super(message);
    }
}
