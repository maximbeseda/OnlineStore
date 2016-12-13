package ua.com.store.dao;

import ua.com.store.enums.StatusEnum;
import ua.com.store.model.Status;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Status} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 */
public interface StatusDAO extends DataDAO<Status> {
    /**
     * Добавляет статус в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     */
    void add(StatusEnum title, String description);

    /**
     * Возвращает статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     */
    Status get(StatusEnum title);

    /**
     * Возвращает из базы даных статус по-умолчанию.
     */
    Status getDefault();

    /**
     * Удаляет статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     */
    void remove(StatusEnum title);
}
