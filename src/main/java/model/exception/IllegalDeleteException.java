package model.exception;

public class IllegalDeleteException extends RuntimeException {
    public IllegalDeleteException(String message) {
        super(message);
    }
}
