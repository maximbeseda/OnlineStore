package ua.com.store.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ua.com.store.dao.UserDAO;
import ua.com.store.model.Role;
import ua.com.store.model.User;
import ua.com.store.repository.RoleRepository;
import ua.com.store.repository.UserRepository;

import java.util.List;

/**
 * Класс реализует методы доступа объектов класса {@link User}
 * в базе данных интерфейса {@link UserDAO}, наследует родительский
 * абстрактный класс {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link UserRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 */
@Repository
public class UserDAOImpl extends DataDAOImpl<User> implements UserDAO {

    /**
     * ID роли клиента в базе данных.
     */
    private static Long CLIENT_ROLE_ID = 1L;

    /**
     * ID роли адмиистратора в базе данных.
     */
    private static Long ADMIN_ROLE_ID = 2L;

    /**
     * ID роли менеджера в базе данных.
     */
    private static Long MANAGER_ROLE_ID = 3L;
    /**
     * Реализация репозитория {@link UserRepository} для работы пользователей с базой данных.
     */
    private UserRepository userRepository;

    /**
     * Реализация репозитория {@link RoleRepository} для работы  ролями пользователей с базой данных.
     */
    private RoleRepository roleRepository;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    public UserDAOImpl(UserRepository userRepository, RoleRepository roleRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Возвращает пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     */
    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * Возвращает пользователя из базы даных, у которого совпадает уникальный
     * логин с значением входящего параметра.

     */
    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Возвращает главного администратора сайта.
     */
    @Override
    public User getMainAdministrator() {
        Role admin = roleRepository.findOne(ADMIN_ROLE_ID);
        return userRepository.findAllByRole(admin).get(0);
    }

    /**
     * Возвращает список всех администраторов сайта.
     */
    @Override
    public List<User> getAdministrators() {
        Role admin = roleRepository.findOne(ADMIN_ROLE_ID);
        return userRepository.findAllByRole(admin);
    }

    /**
     * Возвращает список всех менеджеров сайта.
     */
    @Override
    public List<User> getManagers() {
        Role manager = roleRepository.findOne(MANAGER_ROLE_ID);
        return userRepository.findAllByRole(manager);
    }

    /**
     * Возвращает список всех клиентов сайта.
     */
    @Override
    public List<User> getClients() {
        Role client = roleRepository.findOne(CLIENT_ROLE_ID);
        return userRepository.findAllByRole(client);
    }

    /**
     * Возвращает авторизированого пользователя.
     */
    @Override
    public User getAuthenticatedUser() {
        User user;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = (User) authentication.getPrincipal();
        } catch (Exception ex) {
            //ex.printStackTrace();
            user = new User();
        }
        return user;
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     */
    @Override
    public void remove(String name) {
        userRepository.deleteByName(name);
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     */
    @Override
    public void remove(Role role) {
        userRepository.deleteAllByRole(role);
    }
}
