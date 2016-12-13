package ua.com.store.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает сущность "Товар", наследует класс {@link Model}.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "products") указывает на таблицу "products", в которой будут храниться объекты.
 */
@Entity
@Table(name = "Products")
public class Product extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Набор вожможных для использованния символов по-умолчанию.
     */
    public static final char[] CODE_PATTERN = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    /**
     * Длина возвращаемой строки по-умолчанию.
     */
    public static final int CODE_LENGTH = 5;

    /**
     * Артикль товара. Значение поля сохраняется в колонке "article". Не может быть null.
     */
    @Column(name = "article", nullable = false)
    private int article;

    /**
     * Название товара. Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * URL товара. Значение поля сохраняется в колонке "url". Не может быть null.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * Параметры товара. Значение поля сохраняется в колонке "parameters".
     */
    @Column(name = "parameters")
    private String parameters;

    /**
     * Описание товара. Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description;

    /**
     * Категория товара.
     * Значение поля (id объекта category) сохраняется в колонке "category_id". Не может быть null.
     * Между объектами классов {@link Product} и
     * {@link Category} связь многие-к-одному, а именно каждая
     * много заказов могут иметь одинаковый статус выполнения.
     * Выборка объекта category до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    /**
     * Изображение товара.
     * Значение поля (id объекта photo) сохраняется в колонке "photo_id".
     * Между объектами классов {@link Category} и
     * {@link Photo} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта photo до первого доступа нему, при первом доступе к текущему объекту.
     * Сущности связаны полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /**
     * Цена товара. Значение поля сохраняется в колонке "description". Не может быть null.
     */
    @Column(name = "price", nullable = false)
    private double price;

    /**
     * Изображение товара.
     * К текущему товару можно добраться через поле "product"
     * в объекте класса {@link SalePosition}.
     * Выборка объекта salePosition при первом доступе к нему.
     * Сущность salePosition автоматически удаляется при удалении текущей.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<SalePosition> salePositions = new ArrayList<>();

    /**
     * Конструктр без параметров.
     * Автоматически инициализируются поля article.
     */
    public Product() {
        super();
        title = "";
        url = "";
        parameters = "";
        description = "";
        price = 0.0;
        newArticle();
    }

    /**
     * Конструктор для инициализации основных переменных товара.
     * Автоматически инициализируются поля article.
     */
    public Product(String title, String url, Category category, Photo photo, double price) {
        super();
        this.title = title;
        this.url = url;
        this.category = category;
        this.photo = photo;
        this.price = price;

        parameters = "";
        description = "";
        newArticle();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title)
                .append("\nParameters: ").append(parameters)
                .append("\nDescription: ").append(description)
                .append("\nPrice = ").append(price).append(" UAH");

        if (category != null) {
            sb.append("\nCategory: ").append(category.getTitle());
        }
        return sb.toString();
    }

    /**
     * Генерирует строку для конечного сравнения товаров в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     */
    @Override
    public String toEquals() {
        return getArticle() + getTitle() + getUrl() + getPrice();
    }

    /**
     * Инициализация полей товара.
     */
    public void initialize(String title, String url, String parameters,
                           String description, Category category, Photo photo, double price) {
        setTitle(title);
        setUrl(url);
        setParameters(parameters);
        setDescription(description);
        setCategory(category);
        setPhoto(photo);
        setPrice(price);
    }

    /**
     * Генерирует новый артикль товара.
     */
    public void newArticle() {
        article = Integer.parseInt(createRandomString(CODE_PATTERN, CODE_LENGTH));
    }
    
    public int getArticle() {
        return article;
    }
    
    public void setArticle(int article) {
        this.article = article;
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
    
    public String getParameters() {
        return parameters;
    }
    
    public void setParameters(String parameters) {
        this.parameters = parameters == null ? "" : parameters;
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
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price > 0 ? price : 0;
    }
    
    public List<SalePosition> getSalePositions() {
        return salePositions;
    }
    
    public void setSalePositions(List<SalePosition> salePositions) {
        this.salePositions = salePositions;
    }
}
