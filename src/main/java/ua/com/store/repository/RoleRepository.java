package ua.com.store.repository;

import ua.com.store.enums.RoleEnum;
import ua.com.store.model.Role;

/**
 * Репозиторий для объектов класса {@link Role}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 */
public interface RoleRepository extends MainRepository<Role, Long> {
    /**
     * Возвращает роль из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     */
    Role findByTitle(RoleEnum title);

    /**
     * Удаляет роль из базы даных по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     */
    void deleteByTitle(RoleEnum title);
}

