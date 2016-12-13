package ua.com.store.dao;

import ua.com.store.enums.RoleEnum;
import ua.com.store.model.Role;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Role} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 */
public interface RoleDAO extends DataDAO<Role> {

    /**
     * Добавляет роль в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     */
    void add(RoleEnum title, String description);

    /**
     * Возвращает роль из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     */
    Role get(RoleEnum title);

    /**
     * Возвращает из базы даных роль по-умолчанию.
     */
    Role getDefault();

    /**
     * Удаляет роль из базы даных по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     */
    void remove(RoleEnum title);
}
