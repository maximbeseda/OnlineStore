package ua.com.store.repository;

import ua.com.store.model.Order;

/**
 * Репозиторий для объектов класса {@link Order}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 */
public interface OrderRepository extends MainRepository<Order, Long> {
    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    Order findByNumber(String number);

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    void deleteByNumber(String number);
}