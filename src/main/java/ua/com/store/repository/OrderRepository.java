package ua.com.store.repository;

import ua.com.store.model.Order;

/**
 * Репозиторий для объектов класса {@link Order}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Максим Беседа
 * @see MainRepository
 * @see Order
 */
public interface OrderRepository extends MainRepository<Order, Long> {
    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером
     * для возвращения.
     */
    Order findByNumber(String number);

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    void deleteByNumber(String number);
}