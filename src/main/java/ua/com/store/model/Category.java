package ua.com.store.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает сущность "Категория товаров", наследует класс {@link Model}.
 * Категория описывает набор товаров, которые имеют общие характеристики.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "categories") указывает на таблицу "categories", в которой будут храниться объекты.
 */
@Entity
@Table(name = "Categories")
public class Category extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название категории. Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * URL категории. Значение поля сохраняется в колонке "url". Не может быть null.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * Описание категории. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Изображение категории.
     * Значение поля (id объекта photo) сохраняется в колонке "photo_id".
     * Между объектами классов {@link Category}
     * и {@link Photo} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта photo до первого доступа нему, при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /**
     * Список товаров, которые относятся к данной категории.
     * К текущей категории можно добраться через поле "category"
     * в объекте класса {@link Category}.
     * Выборка объектов products при первом доступе к нему.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Category() {
        super();
        this.title = "";
        this.url = "";
        this.description = "";
    }

    public Category(String title, String url, String description, Photo photo) {
        super();
        this.title = title;
        this.url = url;
        this.description = description;
        this.photo = photo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTitle: ").append(title)
                .append("\nUrl: ").append(url)
                .append("\nDiscription: ").append(description);
        return sb.toString();
    }

    /**
     * Генерирует строку для конечного сравнения категорий в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     */
    @Override
    public String toEquals() {
        if (title.isEmpty() || url.isEmpty()) {
            return super.toString();
        } else {
            return getTitle() + getUrl();
        }
    }

    /**
     * Инициализация полей категории.
     */
    public void initialize(String title, String url, String description, Photo photo) {
        setTitle(title);
        setUrl(url);
        setDescription(description);
        setPhoto(photo);
    }

    /**
     * Добавляет товар в список текущей категории.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Добавляет список товаров в список текущей категории.
     */
    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    /**
     * Удаляет товар из списка текущей категории.
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }

    /**
     * Удаляет список товаров из списка текущей категории.
     */
    public void removeProducts(List<Product> products) {
        this.products.removeAll(products);
    }

    /**
     * Очищает список товаров products.
     */
    public void clearProducts() {
        products.clear();
    }

    public List<Product> getProducts() {
        return getUnmodifiableList(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? "" : url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
