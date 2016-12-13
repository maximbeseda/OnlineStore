package ua.com.store.repository;

import ua.com.store.model.Category;

/**
 * Репозиторий для объектов класса {@link Category}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 */
public interface CategoryRepository extends MainRepository<Category, Long> {
    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     */
    Category findByUrl(String url);

    /**
     * Удаляет категрию из базы даных, у которого совпадает поле url.
     */
    void deleteByUrl(String url);
}
