package ua.com.store.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.store.dao.PhotoDAO;
import ua.com.store.model.Photo;
import ua.com.store.repository.PhotoRepository;

/**
 * Класс реализует методы доступа объектов класса {@link Photo}
 * в базе данных интерфейса {@link PhotoDAO}, наследует родительский
 * абстрактный класс {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link PhotoRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 */
@Repository
public class PhotoDAOImpl extends DataDAOImpl<Photo> implements PhotoDAO {
    /**
     * Реализация репозитория {@link PhotoRepository} для работы изображений с базой данных.
     */
    private PhotoRepository repository;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    public PhotoDAOImpl(PhotoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    @Override
    public Photo get(String title) {
        return repository.findByTitle(title);
    }

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    @Override
    public void remove(String title) {
        repository.deleteByTitle(title);
    }
}
