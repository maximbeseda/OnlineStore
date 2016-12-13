package ua.com.store.model;

import javax.persistence.*;

/**
 * Класс описывает сущность "Изображение", наследует класс {@link Model}.
 * Объект изображение имеет две ссылки на файли-изображение в файлвой системе.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "photos") указывает на таблицу "photos", в которой будут храниться объекты.
 */
@Entity
@Table(name = "Photos")
public class Photo extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название изображения. Значение поля сохраняется в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Строка-ссылка на малое изображения. Значение поля сохраняется в колонке "photo_link_short".
     */
    @Column(name = "photo_link_short")
    private String photoLinkShort;

    /**
     * Строка-ссылка на большое изображения. Значение поля сохраняется в колонке "photo_link_long".
     */
    @Column(name = "photo_link_long")
    private String photoLinkLong;

    /**
     * Товар, к которому относится данное изображение. К даному объекту можно добраться
     * через поле "photo" в объекте класса {@link Product}.
     * Выборка объекта product при первом доступе к нему.
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "photo")
    private Product product;

    /**
     * Категория, к которой относится данное изображение. К текущему изображению можно добраться
     * через поле "photo" в объекте класса {@link Category}.
     * Выборка объекта category при первом доступе к нему.
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "photo")
    private Category category;
    
    public Photo() {
        super();
        title = "";
        photoLinkShort = "";
        photoLinkLong = "";
    }
    
    public Photo(String title, String photoLinkShort, String photoLinkLong) {
        super();
        this.title = title;
        this.photoLinkShort = photoLinkShort;
        this.photoLinkLong = photoLinkLong;
    }
    
    public Photo(String title, String photoLinkShort) {
        super();
        this.title = title;
        this.photoLinkShort = photoLinkShort;
        photoLinkLong = "";
    }

    /**
     * Возвращает описание изображения.
     * Переопределенный метод родительского класса {@link Model}.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTitle: ").append(title)
                .append("\nphoto short link: ").append(photoLinkShort)
                .append("\nphoto long link: ").append(photoLinkLong);
        return sb.toString();
    }

    /**
     * Инициализация полей изображения.
     */
    public void initialize(String title, String photoLinkShort, String photoLinkLong) {
        setTitle(title);
        setPhotoLinkShort(photoLinkShort);
        setPhotoLinkLong(photoLinkLong);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }
    
    public String getPhotoLinkShort() {
        return photoLinkShort;
    }
    
    public void setPhotoLinkShort(String photoLinkShort) {
        this.photoLinkShort = photoLinkShort == null ? "" : photoLinkShort;
    }
    
    public String getPhotoLinkLong() {
        return photoLinkLong;
    }
    
    public void setPhotoLinkLong(String photoLinkLong) {
        this.photoLinkLong = photoLinkLong == null ? "" : photoLinkLong;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
}
