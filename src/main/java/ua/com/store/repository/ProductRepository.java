package ua.com.store.repository;

import ua.com.store.model.Product;

import java.util.List;

/**
 * Репозиторий для объектов класса {@link Product}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 */
public interface ProductRepository extends MainRepository<Product, Long> {
    /**
     * Возвращает товар из базы данных, у которого совпадает параметр url.
     */
    Product findByUrl(String url);

    /**
     * Возвращает товар из базы даных, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     */
    Product findByArticle(int article);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр url.
     */
    void deleteByUrl(String url);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр article.
     */
    void deleteByArticle(int article);

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     */
    List<Product> findByCategoryId(long id);
}
