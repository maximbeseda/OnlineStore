package ua.com.store.dao;

import ua.com.store.model.Photo;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Photo} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 */
public interface PhotoDAO extends DataDAO<Photo> {
    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    Photo get(String title);

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    void remove(String title);
}

