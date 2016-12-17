package ua.com.store.model;

import ua.com.store.enums.StatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает сущность "Статус заказов", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "statuses") указывает на таблицу "statuses", в которой будут храниться объекты.
 *
 * @author Максим Беседа
 * @see StatusEnum
 * @see Order
 */
@Entity
@Table(name = "Statuses")
public class Status extends Model {

    /** Номер версии класса необходимый для десериализации и сериализации. */
    private static final long serialVersionUID = 1L;

    /**
     * Название статуса, может принимать одно из значений перечисления {@link StatusEnum}.
     * Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum title;

    /** Описание товара. Значение поля сохраняется в колонке "description". */
    @Column(name = "description")
    private String description;

    /**
     * Список заказов, которые имеют текущий статус.
     * К текущстатусу можно добраться через поле "status"
     * в объекте класса {@link Order}.
     * Выборка объектов orders при первом доступе к ним.
     * Сущности orders автоматически удаляется при удалении текущей.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    public Status() {
        super();
        description = "";
    }

    /**
     * Конструктор для инициализации основных переменных заказа.
     *
     * @param title       Название заказа, может принимать одно из значений перечисления {@link StatusEnum}.
     * @param description Описание статуса.
     */
    public Status(StatusEnum title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    /**
     * Возвращает описание статуса.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание статуса (имя, описание).
     */
    @Override
    public String toString() {
        return "Title: " + title.name() + "\nDescription: " + description;
    }

    /**
     * Добавляет заказы в список текущего статуса.
     *
     * @param order Заказ, который имеет текущий статус.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Добавляет список заказов в список заказов orders.
     *
     * @param orders Список заказов, которые будут иметь текущий статус.
     */
    public void addOrders(List<Order> orders) {
        this.orders.addAll(orders);
    }

    /**
     * Удаляет заказ из списка текущего статуса.
     *
     * @param order Заказ, у которого будет удаленен текущий статус.
     */
    public void removeOrder(Order order) {
        orders.remove(order);
    }

    /**
     * Метод удаляет список заказов из списка orders.
     *
     * @param orders Список заказов, у которых будет удаленен текущий статус.
     */
    public void removeOrders(List<Order> orders) {
        this.orders.removeAll(orders);
    }

    /**
     * Очищает список заказов текущего статуса.
     */
    public void clearOrders() {
        orders.clear();
    }

    public List<Order> getOrders() {
        return getUnmodifiableList(orders);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public StatusEnum getTitle() {
        return title;
    }

    public void setTitle(StatusEnum title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }
}
