package ua.com.store.exception;

/**
 * Исключение генерируется, если данные не найдены в базе данных.
 *
 * @author Максим Беседа
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
