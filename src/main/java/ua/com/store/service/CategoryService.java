package ua.com.store.service;

import ua.com.store.model.Category;
import ua.com.store.service.impl.CategoryServiceImpl;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Category}. Расширяет интерфейс {@link MainService}.
 *
 * @author Максим Беседа
 * @see Category
 * @see MainService
 * @see CategoryServiceImpl
 */
public interface CategoryService extends MainService<Category> {
    /**
     * Возвращает категорию, у которой совпадает параметр url.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} - категория с уникальным url полем.
     */
    Category get(String url);

    /**
     * Удаляет категрию, у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    void remove(String url);
}