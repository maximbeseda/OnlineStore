package ua.com.store.service;

import ua.com.store.enums.StatusEnum;
import ua.com.store.model.Status;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Status}. Расширяет интерфейс {@link MainService}.
 */
public interface StatusService extends MainService<Status> {
    /**
     * Добавляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     */
    void add(StatusEnum title, String description);

    /**
     * Возвращает статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     */
    Status get(StatusEnum title);

    /**
     * Возвращает статус по-умолчанию.
     */
    Status getDefault();

    /**
     * Удаляет статус по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     */
    void remove(StatusEnum title);

}

