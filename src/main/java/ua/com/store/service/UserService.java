package ua.com.store.service;

import ua.com.store.model.Role;
import ua.com.store.model.User;

import java.util.List;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link User}. Расширяет интерфейс {@link MainService}.
 */
public interface UserService extends MainService<User> {
    /**
     * Возвращает пользователя, у которого совпадает
     * имя с значением входящего параметра.
     */
    User getByName(String name);

    /**
     * Возвращает пользователя, у которого совпадает уникальный
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
     * Возвращает список персонала сайта.
     */
    List<User> getPersonnel();

    /**
     * Возвращает авторизированого пользователя.
     */
    User getAuthenticatedUser();

    /**
     * Удаляет пользователя, у которого совпадает
     * имя с значением входящего параметра.
     */
    void removeByName(String name);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     */
    void removeByRole(Role role);

    /**
     * Удаляет список персонала сайта.
     */
    void removePersonnel();
}
