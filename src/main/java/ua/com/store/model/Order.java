package ua.com.store.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс описывает сущность "Заказы", наследует класс {@link Model}.
 * Заказ описывает торговые позиции, клиента, сделавшего заказ, и менеджера, который обработал заказ.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "orders") указывает на таблицу "orders", в которой будут храниться объекты.
 *
 * @author Максим Беседа
 * @see Status
 * @see User
 * @see SalePosition
 */
@Entity
@Table(name = "Orders")
public class Order extends Model {

    /** Номер версии класса необходимый для десериализации и сериализации. */
    private static final long serialVersionUID = 1L;

    /** Номер заказа. Значение поля сохраняется в колонке "number". Не может быть null. */
    @Column(name = "number", nullable = false)
    private String number;

    /** Дата модификации заказа. Значение поля сохраняется в колонке "date". Не может быть null. */
    @Column(name = "date", nullable = false)
    private String date;

    /** Адрес доставки заказа. Значение поля сохраняется в колонке "shipping_address". */
    @Column(name = "shipping_address")
    private String shippingAddress;

    /** Детали доставки заказа. Значение поля сохраняется в колонке "shipping_details". */
    @Column(name = "shipping_details")
    private String shippingDetails;

    /** Описание заказа. Значение поля сохраняется в колонке "description". */
    @Column(name = "description")
    private String description;

    /**
     * Статус заказа.
     * Значение поля (id объекта status) сохраняется в колонке "status_id".
     * Между объектами классов {@link Order} и
     * {@link Status} связь многие-к-одному, а именно
     * много разных заказов могут иметь одинаковый статус выполнения.
     * Выборка объекта status до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    /**
     * Клиент, оформивший заказ.
     * Значение поля (id объекта client) сохраняется в колонке "client_id".
     * Между объектами классов {@link Order} и
     * {@link User} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта client до первого доступа нему, при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    /**
     * Менеджер, обработавший заказ.
     * Значение поля (id объекта manager) сохраняется в колонке "manager_id".
     * Между объектами классов {@link Order} и
     * {@link User} связь много-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта manager до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    /**
     * Список торговых позиция текущего заказу.
     * К текущему заказу можно добраться через поле "order"
     * в объекте класса {@link SalePosition}.
     * Выборка продаж при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<SalePosition> salePositions = new ArrayList<>();

    /**
     * Конструктр без параметров.
     * Автоматически инициализируются поля номер и дата модификации заказа.
     */
    public Order() {
        super();
        shippingAddress = "";
        shippingDetails = "";
        description = "";
        number = createRandomString();
        date = dateToString(new Date());
    }

    /**
     * Конструктор для инициализации основных переменных заказа.
     *
     * @param status        Статус заказа.
     * @param client        Клиент, оформивший заказ.
     * @param salePositions Список торговых позиция.
     */
    public Order(Status status, User client, List<SalePosition> salePositions) {
        super();
        this.status = status;
        this.client = client;

        addSalePositions(salePositions);

        shippingAddress = "";
        shippingDetails = "";
        description = "";
        number = createRandomString();
        date = dateToString(new Date());
    }

    /**
     * Возвращает описание заказа.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание заказа (номер, статус, дата, информация о клиенте,
     * информация о менеджере, адрес и детали доставкиб описание, торговые позиции).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(number).append(", ")
                .append(status.getDescription()).append(",\n").append(date);

        if (client != null) {
            sb.append("\n\nClient: ").append(client.getName())
                    .append("\ne-mail: ").append(client.getEmail())
                    .append("\nphone: ").append(client.getPhone()).append("\n");
        }

        if (manager != null) {
            sb.append("\n").append(manager.getRole().getDescription())
                    .append(" ").append(manager.getName()).append("\n");
        }

        if (!shippingAddress.isEmpty()) {
            sb.append("\nShipping address: ").append(shippingAddress);
        }
        if (!shippingDetails.isEmpty()) {
            sb.append("\nShipping details: ").append(shippingDetails);
        }
        if (!description.isEmpty()) {
            sb.append("\nDescription: ").append(description);
        }

        if (salePositions != null && !salePositions.isEmpty()) {
            sb.append("\nSale Positions: ");
            int count = 1;
            for (SalePosition salePosition : salePositions) {
                sb.append("\n").append(count++).append(") ").append(salePosition.getProduct().getTitle())
                        .append(", № ").append(salePosition.getProduct().getId()).append(",\n")
                        .append(salePosition.getNumber()).append(" x ")
                        .append(salePosition.getProduct().getPrice()).append(" = ")
                        .append(salePosition.getPrice()).append(" UAH;");
            }
            sb.append("\n\nPRICE = ").append(getPrice()).append(" UAH");
        }
        return sb.toString();
    }

    /**
     * Генерирует строку для конечного сравнения заказа в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа {@link String} - номер заказа.
     */
    @Override
    public String toEquals() {
        return getNumber();
    }

    /**
     * Инициализация полей заказа.
     *
     * @param number          Номер заказа.
     * @param date            Дата модификации заказа.
     * @param shippingAddress Адрес доставки заказа.
     * @param shippingDetails Детали доставки заказа.
     * @param description     Описание заказа.
     * @param status          Статус заказа.
     * @param client          Клиент, оформивший заказ.
     * @param manager         Менеджер, обработавший заказ.
     */
    public void initialize(String number, Date date, String shippingAddress, String shippingDetails,
                           String description, Status status, User client, User manager) {
        setNumber(number);
        setDate(date);
        setShippingAddress(shippingAddress);
        setShippingDetails(shippingDetails);
        setDescription(description);
        setStatus(status);
        setClient(client);
        setManager(manager);
    }

    /**
     * Добавляет торговую позицию в текущий заказа.
     *
     * @param salePosition Торговая позиция, которая будет добавлена в заказ.
     */
    public void addSalePosition(SalePosition salePosition) {
        salePositions.add(salePosition);
        if (salePosition.getOrder() != this) {
            salePosition.setOrder(this);
        }
    }

    /**
     * Добавляет список торговых позиций в текущий заказ.
     *
     * @param salePositions Список торговых позиций, которые будут дабавлены в заказ.
     */
    public void addSalePositions(List<SalePosition> salePositions) {
        this.salePositions.addAll(salePositions);
        for (SalePosition salePosition : salePositions) {
            if (salePosition.getOrder() != this) {
                salePosition.setOrder(this);
            }
        }
    }

    /**
     * Удаляет торговую позицию из текущего заказа.
     *
     * @param salePosition Торговая позиция, которая будет удалена из заказу.
     */
    public void removeSalePosition(SalePosition salePosition) {
        salePositions.remove(salePosition);
    }

    /**
     * Удаляет список торговых позиция из текущего заказа.
     *
     * @param salePositions Список торговых позиция, которые будут удалены из заказа.
     */
    public void removeSalePositions(List<SalePosition> salePositions) {
        this.salePositions.removeAll(salePositions);
    }

    /**
     * Очищает список торговых позиция текущего заказа.
     */
    public void clearSalePositions() {
        salePositions.clear();
    }

    public List<SalePosition> getSalePositions() {
        return getUnmodifiableList(salePositions);
    }

    public void setSalePositions(List<SalePosition> salePositions) {
        this.salePositions = salePositions;
        for (SalePosition salePosition : this.salePositions) {
            if (salePosition.getOrder() != this) {
                salePosition.setOrder(this);
            }
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? "" : number;
    }

    /** Генерирует новый номер заказа. */
    public void newNumber() {
        number = createRandomString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date == null ? "" : dateToString(date);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress == null ? "" : shippingAddress;
    }

    public String getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails == null ? "" : shippingDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    /**
     * Возвращает цену заказа - общую стоимость всех торговых позиция.
     *
     * @return Значение типа double - цена заказа.
     */
    public double getPrice() {
        double price = 0;
        for (SalePosition salePosition : salePositions) {
            price += salePosition.getPrice();
        }
        return price;
    }
}
