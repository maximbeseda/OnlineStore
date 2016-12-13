package ua.com.store.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.store.service.RoleService;
import ua.com.store.service.UserService;

/**
 * Класс-контроллер страниц, предназначеных для управления пользователей менеджерами. К даному контроллеру и соответствующим
 * страницам могут обращатсья пользователи, имеющие роль-админстратор и -менеджер.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/managers") сообщаем, что данный контроллер
 * будет обрабатывать запрос, URI которого "/managers".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 */
@Controller
@RequestMapping(value = "/managers")
public class ManagerUsersController {
    /**
     * Объект сервиса для работы с пользователями.
     */
    private UserService userService;

    /**
     * Объект сервиса для работы с ролями пользователей.
     */
    private RoleService roleService;

    /**
     * Конструктор для инициализации основных переменных контроллера страниц для менеджеров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     */
    @Autowired
    public ManagerUsersController(UserService userService, RoleService roleService) {
        super();
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Возвращает всех пользователей на страницу "managers/user/all".
     * URL запроса "/managers/users", метод GET.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView viewAllPersonnel(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getPersonnel());
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("managers/user/all");
        return modelAndView;
    }

    /**
     * Возвращает пользователя с уникальным кодом id на страницу "managers/user/one".
     * URL запроса "/managers/view_user_{id}", метод GET.
     */
    @RequestMapping(value = "/view_user_{id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.get(id));
        modelAndView.addObject("admin_role", roleService.getAdministrator());
        modelAndView.addObject("manager_role", roleService.getManager());
        modelAndView.addObject("auth_user", userService.getAuthenticatedUser());
        modelAndView.setViewName("managers/user/one");
        return modelAndView;
    }
}
