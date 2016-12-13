package ua.com.store.dao;

import ua.com.store.model.Model;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс описывает стандартный (общий) набор методов для работы с базой данных.
 * Объекты для работы должны быть наследниками класса {@link Model}.
 */
public interface DataDAO<T extends Model> {

    /**
     * Добавление модели в базу данных.
     */
    void add(T model);

    /**
     * Добавление коллекции моделей в базу данных.
     */
    void add(Collection<T> models);

    /**
     * Обновление существующей модели в базе данных.
     */
    void update(T model);

    /**
     * Получение модели по уникальному коду id в базе данных.
     */
    T get(Long id);

    /**
     * Получение всех моделей из базы данных.
     */
    List<T> getAll();

    /**
     * Удаление модели из базы данных.
     */
    void remove(T model);

    /**
     * Удаление модели из базы данных по уникальному коду.
     */
    void remove(Long id);

    /**
     * Удаление коллекции моделей из базы данных.
     */
    void remove(Collection<T> models);

    /**
     * Удаление всех моделей из базы данных.
     */
    void removeAll();
}