package ua.com.store.service;

import ua.com.store.enums.RoleEnum;
import ua.com.store.model.Role;

import java.util.List;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Role}. Расширяет интерфейс {@link MainService}.
 */
public interface RoleService extends MainService<Role> {
    /**
     * Добавляет роль по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     */
    void add(RoleEnum title, String description);

    /**
     * Возвращает роль по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     */
    Role get(RoleEnum title);

    /**
     * Возвращает роль администратора.
     */
    Role getAdministrator();

    /**
     * Возвращает роль менеджера.
     */
    Role getManager();

    /**
     * Возвращает роль по-умолчанию.
     */
    Role getDefault();

    /**
     * Возвращает список ролей персонала сайта.
     */
    List<Role> getPersonnel();

    /**
     * Удаляет роль по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     */
    void remove(RoleEnum title);
}
