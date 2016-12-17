package ua.com.store.dao;

import ua.com.store.dao.impl.OrderDAOImpl;
import ua.com.store.model.Order;

/**
 * Интерфейс описывает набор методов для работы объектов класса {@link Order} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 *
 * @author Максим Беседа
 * @see DataDAO
 * @see OrderDAOImpl
 * @see Order
 */
public interface OrderDAO extends DataDAO<Order> {
    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером с значением входящего параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером для возвращения.
     */
    Order get(String number);

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    void remove(String number);
}

