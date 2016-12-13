package ua.com.store.dao;

import ua.com.store.model.SalePosition;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link SalePosition} с базой данных.
 * Наследует интерфейс {@link DataDAO}, своих дополнительных методв
 * не имеет.
 */
public interface SalePositionDAO extends DataDAO<SalePosition> {
}
