package ua.com.store.repository;

import ua.com.store.enums.StatusEnum;
import ua.com.store.model.Status;

/**
 * Репозиторий для объектов класса {@link Status}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 */
public interface StatusRepository extends MainRepository<Status, Long> {
    /**
     * Возвращает статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     */
    Status findByTitle(StatusEnum title);

    /**
     * Удаляет статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     */
    void deleteByTitle(StatusEnum title);
}
