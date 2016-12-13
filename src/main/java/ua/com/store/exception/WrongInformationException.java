package ua.com.store.exception;

/**
 * Исключение генерируется, если вводимая информация оо объекте {@link ua.com.store.model.Model} не верна,
 * равна запрещенному значению и т.д.
 */
public class WrongInformationException extends RuntimeException {

    public WrongInformationException() {
        super();
    }

    public WrongInformationException(String message) {
        super(message);
    }
}