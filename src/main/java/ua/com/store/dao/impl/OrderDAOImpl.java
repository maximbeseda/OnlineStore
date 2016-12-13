package ua.com.store.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.store.dao.OrderDAO;
import ua.com.store.model.Order;
import ua.com.store.repository.OrderRepository;

/**
 * Класс реализует методы доступа объектов класса {@link Order}
 * в базе данных интерфейса {@link OrderDAO}, наследует родительский
 * абстрактный класс {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link OrderRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 */
@Repository
public class OrderDAOImpl extends DataDAOImpl<Order> implements OrderDAO {
    /**
     * Реализация репозитория {@link OrderRepository} для работы категорий с базой данных.
     */
    private OrderRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    public OrderDAOImpl(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    @Override
    public Order get(String number) {
        return repository.findByNumber(number);
    }

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    @Override
    public void remove(String number) {
        repository.deleteByNumber(number);
    }
}
