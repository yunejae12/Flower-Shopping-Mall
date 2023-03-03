package shopping.flowershop.exception;

public class NotEnoughStackException extends RuntimeException{

    public NotEnoughStackException() {
        super();
    }

    public NotEnoughStackException(String message) {
        super(message);
    }

    public NotEnoughStackException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStackException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughStackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
