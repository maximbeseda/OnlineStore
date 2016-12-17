package ua.com.store.dao;

import ua.com.store.model.Category;

/**
 * Интерфейс описывает набор методов для работы объектов класса {@link Category} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 *
 * @author Максим Беседа
 * @see DataDAO
 * @see ua.com.store.dao.impl.CategoryDAOImpl
 * @see Category
 */
public interface CategoryDAO extends DataDAO<Category> {
    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} - категория с уникальным url полем.
     */
    Category get(String url);

    /**
     * Удаляет категрию из базы даных, у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    void remove(String url);
}