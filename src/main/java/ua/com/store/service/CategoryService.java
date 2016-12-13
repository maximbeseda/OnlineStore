package ua.com.store.service;

import ua.com.store.model.Category;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Category}. Расширяет интерфейс {@link MainService}.
 */
public interface CategoryService extends MainService<Category> {
    /**
     * Возвращает категорию, у которой совпадает параметр url.
     */
    Category get(String url);

    /**
     * Удаляет категрию, у которого совпадает поле url.
     */
    void remove(String url);
}