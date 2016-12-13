package ua.com.store.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс представляет абстрактную модель сущностей, не описывает сущности как таковой.
 * Класс не отображается на отдельную таблицу в базе данных,
 * реализует интерфейс Serializable, может быть сериализован.
 * Аннотация @MappedSuperclass аннотация определяет класс, описанные
 * свойства и методы которого будут применены в классах-наследниках.
 */
@MappedSuperclass
public abstract class Model implements Serializable {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Набор вожможных для использованния символов по-умолчанию.
     */
    protected static final char[] CODE_PATTERN = {'A', 'B', 'C', 'D', 'E', 'F', 'K', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    /**
     * Длина возвращаемой строки по-умолчанию.
     */
    protected static final int CODE_LENGTH = 6;

    /**
     * Строка-формат для даты по-умолчанию.
     */
    protected static final String DATE_PATTERN = "EEE, d MMM yyyy, HH:mm:ss";

    /**
     * Название (код) часового пояса по-умолчанию.
     */
    protected static final String TIME_ZONE = "GMT+3";

    /**
     * Уникальный код обьекта.
     * Аннотация @Id говорит о том что поле является ключем для текущего объекта.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Model() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Model)) {
            return false;
        }
        Model other = (Model) obj;
        return (this.toEquals().equals(other.toEquals()));
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : toString().hashCode();
    }

    /**
     * Генерирует строку для конечного сравнения объектов в методе equals().
     * Что бы в дочернем классе не переопределять весь метод equals(), можно
     * переопределить тьлько этот метод.
     */
    public String toEquals() {
        return toString();
    }

    /**
     * Возвращает рандомную строку из набор символов и длинны по-умолчанию.
     */
    public static String createRandomString() {
        return createRandomString(CODE_PATTERN, CODE_LENGTH);
    }

    /**
     * Возвращает рандомную строку используя набор символов pattern длиной length.
     */
    public static String createRandomString(char[] pattern, int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int number = new Random().nextInt(pattern.length);
            char ch = pattern[number];
            builder.append(ch);
        }

        return builder.toString();
    }

    /**
     * Конвертирует дату типа Date в строку используя для работы входящими параметрами
     * формат даты {@value DATE_PATTERN} и часовой пояс (@value TIME_ZONE} по-умолчанию.
     */
    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        TimeZone timeZone = TimeZone.getTimeZone(TIME_ZONE);
        return dateToStringWithFormat(date, dateFormat, timeZone);
    }

    /**
     * Конвертирует дату типа Date в строку используя для работы входящими параметрами
     * формат даты и часовой пояс.
     */
    public static String dateToStringWithFormat(Date date, DateFormat dateFormat, TimeZone timeZone) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Конвертирует входящий список возращает лист только для чтений.
     * Если входной параметер - лист равен null или пустой,
     * тогда метод возвращает пустой список.
     */
    public static <T> List<T> getUnmodifiableList(List<T> list) {
        return list == null || list.isEmpty() ? Collections.EMPTY_LIST : Collections.unmodifiableList(list);
    }
}