package ua.com.store.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.store.dao.DataDAO;
import ua.com.store.exception.BadRequestException;
import ua.com.store.exception.WrongInformationException;
import ua.com.store.model.Model;
import ua.com.store.service.MainService;

import java.util.List;

/**
 * Класс сервисного слоя, который реализует основные методы доступа к
 * объектам наследникам родительского класса {@link Model}, описаные в
 * интерфейсе {@link MainService}. Класс должен наследоваться дочерними классами,
 * которые будут описывать поведение объектов-наследников родительского класса {@link Model}.
 * Для работы методы используют объект DAO интерфейса {@link DataDAO}, возвращаемый абстрактным
 * методом dao, реализацию которого каждый наследник берет на себя.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 */
public abstract class MainServiceImpl<T extends Model> implements MainService<T> {
    /**
     * Реализация интерфейса {@link DataDAO} для работы моделей с базой данных.
     */
    private DataDAO<T> dao;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     */
    public MainServiceImpl(DataDAO<T> dao) {
        super();
        this.dao = dao;
    }

    /**
     * Добавление модели в базу данных.
     */
    @Override
    @Transactional
    public void add(T model) {
        if (model != null) {
            dao.add(model);
        }
    }

    /**
     * Добавление коллекции моделей в базу данных.
     */
    @Override
    @Transactional
    public void add(List<T> models) {
        if (models != null && !models.isEmpty()) {
            dao.add(models);
        }
    }

    /**
     * Обновление существующей модели в базе данных.
     */
    @Override
    @Transactional
    public void update(T model) {
        if (model != null) {
            dao.update(model);
        }
    }

    /**
     * Получение модели по уникальному коду id в базе данных. Режим только для чтения.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link Model} -  модель с кодом id.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр id.
     * @throws BadRequestException       Бросает исключение, если не найдена модель с входящим параметром id.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(Long id) throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }

        T model = dao.get(id);
        if (model == null) {
            throw new BadRequestException("Can't find model by id " + id + "!");
        }
        return dao.get(id);
    }

    /**
     * Получение всех моделей из базы данных. Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * Удаление модели из базы данных.
     */
    @Override
    @Transactional
    public void remove(T model) {
        if (model != null) {
            dao.remove(model);
        }
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр id.
     */
    @Override
    @Transactional
    public void remove(Long id) throws WrongInformationException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        dao.remove(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     */
    @Override
    @Transactional
    public void remove(List<T> models) {
        if (models != null && !models.isEmpty()) {
            dao.remove(models);
        }
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    @Transactional
    public void removeAll() {
        dao.removeAll();
    }
}
