package ua.com.store.dao;

import ua.com.store.dao.impl.PhotoDAOImpl;
import ua.com.store.model.Photo;

/**
 * Интерфейс описывает набор методов для работы объектов класса {@link Photo} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 *
 * @author Максим Беседа
 * @see DataDAO
 * @see PhotoDAOImpl
 * @see Photo
 */
public interface PhotoDAO extends DataDAO<Photo> {
    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объекта-изображение.
     */
    Photo get(String title);

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаления.
     */
    void remove(String title);
}

