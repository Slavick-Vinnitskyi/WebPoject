package model.exception;

public class UserOrCarNotFountException extends RuntimeException {
    public UserOrCarNotFountException(String message) {
        super(message);
    }
}
