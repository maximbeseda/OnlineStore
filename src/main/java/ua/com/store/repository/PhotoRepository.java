package ua.com.store.repository;

import ua.com.store.model.Photo;

/**
 * Репозиторий для объектов класса {@link Photo}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 */
public interface PhotoRepository extends MainRepository<Photo, Long> {
    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    Photo findByTitle(String title);

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    void deleteByTitle(String title);
}
