package ua.com.store.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.store.dao.RoleDAO;
import ua.com.store.enums.RoleEnum;
import ua.com.store.model.Role;
import ua.com.store.repository.RoleRepository;

/**
 * Класс реализует методы доступа объектов класса {@link Role}
 * в базе данных интерфейса {@link RoleDAO}, наследует родительский
 * абстрактный класс {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link RoleRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 */
@Repository
public class RoleDAOImpl extends DataDAOImpl<Role> implements RoleDAO {
    /**
     * Реализация репозитория {@link RoleRepository} для работы ролей пользователей с базой данных.
     */
    private RoleRepository repository;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    public RoleDAOImpl(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Добавляет роль в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     */
    @Override
    public void add(RoleEnum title, String description) {
        repository.save(new Role(title, description));
    }

    /**
     * Возвращает роль из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     */
    @Override
    public Role get(RoleEnum title) {
        return repository.findByTitle(title);
    }

    /**
     * Возвращает из базы даных роль по-умолчанию.
     */
    @Override
    public Role getDefault() {
        return repository.findOne((long) 1);
    }

    /**
     * Удаляет роль из базы даных по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     */
    @Override
    public void remove(RoleEnum title) {
        repository.deleteByTitle(title);
    }
}
