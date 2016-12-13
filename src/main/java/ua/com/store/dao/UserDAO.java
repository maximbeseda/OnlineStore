package ua.com.store.dao;

import ua.com.store.model.Role;
import ua.com.store.model.User;

import java.util.List;

/**
 * Интерфейс описывает набор методов для работы объектов класса {@link User} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 */
public interface UserDAO extends DataDAO<User> {
    /**
     * Возвращает пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     */
    User getByName(String name);

    /**
     * Возвращает пользователя из базы даных, у которого совпадает уникальный
     * логин с значением входящего параметра.
     */
    User getByUsername(String username);

    /**
     * Возвращает главного администратора сайта.
     */
    User getMainAdministrator();

    /**
     * Возвращает список всех администраторов сайта.
     */
    List<User> getAdministrators();

    /**
     * Возвращает список всех менеджеров сайта.
     */
    List<User> getManagers();

    /**
     * Возвращает список всех клиентов сайта.
     */
    List<User> getClients();

    /**
     * Возвращает авторизированого пользователя.
     */
    User getAuthenticatedUser();

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     */
    void remove(String name);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     */
    void remove(Role role);
}
