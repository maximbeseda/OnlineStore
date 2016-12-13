package ua.com.store.service;

import ua.com.store.model.Model;

import java.util.List;

/**
 * Интерфейс сервисного слоя, описывает набор основных методов для работы
 * с объектами  дочерних классов родительского класса {@link Model}.
 */
public interface MainService<T extends Model> {
    /**
     * Добавление модели.
     */
    void add(T model);

    /**
     * Добавление список моделей.
     */
    void add(List<T> models);

    /**
     * Обновление существующей модели.
     */
    void update(T model);

    /**
     * Получение модели по уникальному коду id.
     */
    T get(Long id);

    /**
     * Получение всех моделей.
     */
    List<T> getAll();

    /**
     * Удаление модели.
     */
    void remove(T model);

    /**
     * Удаление модели по уникальному коду.
     */
    void remove(Long id);

    /**
     * Удаление коллекции моделей.
     */
    void remove(List<T> models);

    /**
     * Удаление всех моделей.
     */
    void removeAll();
}
