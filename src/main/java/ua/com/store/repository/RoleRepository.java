package ua.com.store.repository;

import ua.com.store.enums.RoleEnum;
import ua.com.store.model.Role;

/**
 * Репозиторий для объектов класса {@link Role}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Максим Беседа
 * @see MainRepository
 * @see Role
 */
public interface RoleRepository extends MainRepository<Role, Long> {
    /**
     * Возвращает роль из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Role} - роль с уникальным названием.
     */
    Role findByTitle(RoleEnum title);

    /**
     * Удаляет роль из базы даных по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    void deleteByTitle(RoleEnum title);
}

