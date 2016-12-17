package ua.com.store.repository;

import ua.com.store.model.SalePosition;

/**
 * Репозиторий для объектов класса {@link SalePosition}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Максим Беседа
 * @see MainRepository
 * @see SalePosition
 */
public interface SalePositionRepository extends MainRepository<SalePosition, Long> {

}