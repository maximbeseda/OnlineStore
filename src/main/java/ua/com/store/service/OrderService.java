package ua.com.store.service;

import ua.com.store.model.Order;
import ua.com.store.service.impl.OrderServiceImpl;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Order}. Расширяет интерфейс {@link MainService}.
 *
 * @author Максим Беседа
 * @see Order
 * @see MainService
 * @see OrderServiceImpl
 */
public interface OrderService extends MainService<Order> {
    /**
     * Возвращает заказ, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером.
     */
    Order get(String number);

    /**
     * Удаляет заказ, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    void remove(String number);
}