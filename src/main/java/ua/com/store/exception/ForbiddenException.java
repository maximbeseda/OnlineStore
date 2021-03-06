package ua.com.store.exception;

/**
 * Исключение генерируется, если пользователь не имеет достаточных прав для доступа к странице.
 *
 * @author Максим Беседа
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }
}