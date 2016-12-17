package ua.com.store.repository;

import ua.com.store.enums.StatusEnum;
import ua.com.store.model.Status;

/**
 * Репозиторий для объектов класса {@link Status}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Максим Беседа
 * @see MainRepository
 * @see Status
 */
public interface StatusRepository extends MainRepository<Status, Long> {
    /**
     * Возвращает статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     * @return Объект класса {@link Status} - статус с уникальным названием.
     */
    Status findByTitle(StatusEnum title);

    /**
     * Удаляет статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    void deleteByTitle(StatusEnum title);
}
