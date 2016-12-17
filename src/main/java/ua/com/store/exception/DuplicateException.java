package ua.com.store.exception;

/**
 * Исключение генерируется, если данные в базе данных дублируются.
 *
 * @author Максим Беседа
 */
public class DuplicateException extends RuntimeException {

    public DuplicateException() {
        super();
    }

    public DuplicateException(String message) {
        super(message);
    }
}