package ua.com.store.model;

import javax.persistence.*;

/**
 * Класс описывает сущность "Торговая позиция", наследует класс {@link Model}.
 * Торговая позиция составляет товар и количество этого товара.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "sales") указывает на таблицу "sales", в которой будут храниться объекты.
 *
 * @author Максим Беседа
 * @see Product
 * @see Order
 * @see ShoppingCart
 */
@Entity
@Table(name = "Sales")
public class SalePosition extends Model {

    /**
     * Товар текущей торговой позици.
     * Значение поля (id объекта photo) сохраняется в колонке "product_id". Не может быть null.
     * Между объектами классов {@link Product}
     * и {@link SalePosition} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта product до первого доступа нему, при первом доступе к текущему объекту.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    /**
     * Количество товаров в текущей торговой позиции.
     * Значение поля сохраняется в колонке "description". Не может быть null.
     */
    @Column(name = "number", nullable = false)
    private int number;

    /**
     * Заказ, к которому относится текущая торговая позиция
     * Значение поля (id объекта order) сохраняется в колонке "order_id". Не может быть null.
     * Между объектами классов {@link Order} и
     * {@link SalePosition} связь многие-к-одному, а именно каждая
     * много заказов могут иметь одинаковый статус выполнения.
     * Выборка объекта order при первом доступе к нему.
     * Сущность order автоматически удаляется при удалении текущей.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    public SalePosition() {
        super();
        number = 0;
    }

    /**
     * Конструктор для инициализации основных переменных категории.
     *
     * @param product Товар текущей торговой позици.
     * @param number  Количество товаров в текущей торговой позиции.
     */
    public SalePosition(Product product, int number) {
        super();
        this.product = product;
        this.number = number;
    }

    /**
     * Возвращает описание торговой позиции.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание торговой позиции
     * (уникальный код позиции, информация о товаре, количество тваров и общая цена торговой позиции).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SalePosition #" + getId() + ": ");
        sb.append("\n").append(product.getTitle()).append("\n№ ").append(product.getId())
                .append(", ").append(product.getPrice()).append(" UAH")
                .append("\nNumber = ").append(number)
                .append("\nPrice = ").append(getPrice());
        return sb.toString();
    }

    /**
     * Генерирует строку для конечного сравнения торговых позиций в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа {@link String} - результат работы метода сравнения входящего товара toEquals.
     */
    @Override
    public String toEquals() {
        String line = product.toEquals();
        if (getId() != null) {
            line += getId();
        }
        return line;
    }

    /**
     * Возвращает общую стоимость торговой позиции (цена товара * количество).
     *
     * @return Значение типа double - цена торговой пзиции.
     */
    public double getPrice() {
        return product.getPrice() * number;
    }

    /**
     * Увеличивает количество товаров в позиции на 1.
     */
    public void numberIncr() {
        number++;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        number = product == null ? 0 : 1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number > 0 ? number : 0;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
