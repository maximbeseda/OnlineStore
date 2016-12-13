package ua.com.store.repository;

import ua.com.store.model.Role;
import ua.com.store.model.User;

import java.util.List;

/**
 * Репозиторий для объектов класса {@link User}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 */
public interface UserRepository extends MainRepository<User, Long> {
    /**
     * Возвращает пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     */
    User findByName(String name);

    /**
     * Возвращает пользователя из базы даных, у которого совпадает уникальный
     * логин с значением входящего параметра.
     */
    User findByUsername(String username);

    /**
     * Возвращает пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     */
    User findByRole(Role role);

    /**
     * Возвращает всех пользователей из базы данных, у которых
     * совпадают роли с значением входящего параметра.
     */
    List<User> findAllByRole(Role role);

    /**
     * Удаляет всех пользователей из базы данных, у которых
     * совпадают роли с значением входящего параметра.
     */
    void deleteAllByRole(Role role);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     */
    void deleteByName(String name);
}
