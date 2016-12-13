package ua.com.store.dao;

import ua.com.store.model.Product;

import java.util.List;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Product} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 */
public interface ProductDAO extends DataDAO<Product> {
    /**
     * Возвращает товар из базы данных, у которого совпадает параметр url.
     */
    Product getByUrl(String url);

    /**
     * Возвращает товар из базы даных, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     */
    Product getByArticle(int article);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр url.
     */
    void removeByUrl(String url);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр article.
     */
    void removeByArticle(int article);

    /**
     * Удаляет товары из базы даных, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     */
    void removeByCategoryId(long id);

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     */
    List<Product> getListByCategoryId(long id);
}

