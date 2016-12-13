package ua.com.store.service;

import ua.com.store.model.Order;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Order}. Расширяет интерфейс {@link MainService}.
 */
public interface OrderService extends MainService<Order> {
    /**
     * Возвращает заказ, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    Order get(String number);

    /**
     * Удаляет заказ, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    void remove(String number);
}