package vn.com.store.exception;

public final class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }

}
