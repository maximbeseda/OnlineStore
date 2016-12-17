package ua.com.store.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.store.dao.CategoryDAO;
import ua.com.store.model.Category;
import ua.com.store.repository.CategoryRepository;

/**
 * Класс реализует методы доступа объектов класса {@link Category}
 * в базе данных интерфейса {@link CategoryDAO}, наследует родительский
 * абстрактный класс {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link CategoryRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Максим Беседа
 * @see DataDAOImpl
 * @see CategoryDAO
 * @see Category
 * @see CategoryRepository
 */
@Repository
public class CategoryDAOImpl extends DataDAOImpl<Category> implements CategoryDAO {
    /**
     * Реализация репозитория {@link CategoryRepository} для работы категорий с базой данных.
     */
    private CategoryRepository repository;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация интерфейса {@link CategoryRepository}
     *                   для работы категорий с базой данных.
     */
    @Autowired
    public CategoryDAOImpl(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} - категория с уникальным url полем.
     */
    @Override
    public Category get(String url) {
        return repository.findByUrl(url);
    }

    /**
     * Удаляет категрию из базы даных, у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    @Override
    public void remove(String url) {
        repository.deleteByUrl(url);
    }
}

