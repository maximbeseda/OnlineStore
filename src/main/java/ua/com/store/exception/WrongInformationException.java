package ua.com.store.exception;

import ua.com.store.model.Model;

/**
 * Исключение генерируется, если вводимая информация оо объекте {@link Model} не верна,
 * равна запрещенному значению и т.д.
 *
 * @author Максим Беседа
 */
public class WrongInformationException extends RuntimeException {

    public WrongInformationException() {
        super();
    }

    public WrongInformationException(String message) {
        super(message);
    }
}