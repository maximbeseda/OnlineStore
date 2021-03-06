package ua.com.store.dao.impl;

import ua.com.store.dao.DataDAO;
import ua.com.store.model.Model;
import ua.com.store.repository.MainRepository;

import java.util.Collection;
import java.util.List;

/**
 * Абстрактный класс, который реализует основные методы доступа к базе данных
 * интерфейса {@link DataDAO}. Класс должен наследоваться
 * дочерними классами, которые будут описывать поведение объектов-наследников
 * родительского класса {@link Model}. Для работы методы
 * используют объект-репозиторий интерфейса {@link MainRepository},
 * возвращаемый абстрактным методом repository, реализацию которого каждый наследник берет
 * на себя.
 *
 * @author Максим Беседа
 * @param <T> Класс-наследник класса {@link Model}.
 * @see CategoryDAOImpl
 * @see PhotoDAOImpl
 * @see ProductDAOImpl
 * @see RoleDAOImpl
 * @see SalePositionDAOImpl
 * @see StatusDAOImpl
 * @see DataDAOImpl
 * @see DataDAO
 */
public abstract class DataDAOImpl<T extends Model> implements DataDAO<T> {
    /**
     * Реализация репозитория {@link MainRepository} для работы моделей с базой данных.
     */
    private MainRepository<T, Long> repository;

    /**
     * Конструктор для инициализации основных переменных.
     *
     * @param repository Реализация репозитория {@link MainRepository} для работы категорий с базой данных.
     */
    public DataDAOImpl(MainRepository<T, Long> repository) {
        super();
        this.repository = repository;
    }

    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    @Override
    public void add(T model) {
        repository.save(model);
    }

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    @Override
    public void add(Collection<T> models) {
        repository.save(models);
    }

    /**
     * Обновление существующей модели в базе данных.
     *
     * @param model Обновленная модель.
     */
    @Override
    public void update(T model) {
        repository.save(model);
    }

    /**
     * Получение модели по уникальному коду id в базе данных.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link Model} -  модель с кодом id.
     */
    @Override
    public T get(Long id) {
        return repository.findOne(id);
    }

    /**
     * Получение всех моделей из базы данных.
     *
     * @return Объект типа List - список всех моделей.
     */
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    /**
     * Удаление модели из базы данных.
     *
     * @param model Модель для удаления.
     */
    @Override
    public void remove(T model) {
        repository.delete(model);
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     */
    @Override
    public void remove(Long id) {
        repository.delete(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    @Override
    public void remove(Collection<T> models) {
        repository.delete(models);
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
