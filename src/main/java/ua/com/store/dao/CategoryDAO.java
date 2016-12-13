package ua.com.store.dao;

import ua.com.store.model.Category;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Category} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 */
public interface CategoryDAO extends DataDAO<Category> {
    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     */
    Category get(String url);

    /**
     * Удаляет категрию из базы даных, у которого совпадает поле url.
     */
    void remove(String url);
}