package ua.com.store.dao;

import ua.com.store.model.Order;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Order} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 */
public interface OrderDAO extends DataDAO<Order> {
    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    Order get(String number);

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    void remove(String number);
}

